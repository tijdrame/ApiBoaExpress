package com.boa.api.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.domain.ParamEndPoint;
import com.boa.api.domain.Pays;
import com.boa.api.domain.RequestParam;
import com.boa.api.domain.Tracking;
import com.boa.api.domain.Transaction;
import com.boa.api.request.CalculfraisTransfertRequest;
import com.boa.api.request.ConfirmTransfertRequest;
import com.boa.api.request.EnvoiTransfertRequest;
import com.boa.api.request.TransactionByNumRequest;
import com.boa.api.request.TransactionByRefRequest;
import com.boa.api.response.CalculfraisTransfertResponse;
import com.boa.api.response.ConfirmTansfertResponse;
import com.boa.api.response.Confirmdeposit;
import com.boa.api.response.EnvoiTransfertResponse;
import com.boa.api.response.Fraistransaction;
import com.boa.api.response.PaysActifsResponse;
import com.boa.api.response.PaysResp;
import com.boa.api.response.Status;
import com.boa.api.response.TransactionByNumResponse;
import com.boa.api.response.TransactionByRefResponse;
import com.boa.api.service.utils.ICodeDescResponse;
import com.boa.api.service.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiService {

    private final Logger log = LoggerFactory.getLogger(ApiService.class);

    private final TrackingService trackingService;
    private final UserService userService;
    private final Utils utils;
    private final ParamEndPointService endPointService;
    private final TransactionService transactionService;
    private final RequestParamService requestParamService;
    private final PaysService paysService;

    public ApiService(TrackingService trackingService, UserService userService, Utils utils,
            ParamEndPointService endPointService, TransactionService transactionService,
            RequestParamService requestParamService, PaysService paysService) {
        this.trackingService = trackingService;
        this.userService = userService;
        this.utils = utils;
        this.endPointService = endPointService;
        this.transactionService = transactionService;
        this.requestParamService = requestParamService;
        this.paysService = paysService;
    }

    public EnvoiTransfertResponse envoiTransfert(EnvoiTransfertRequest envoiTransfert, HttpServletRequest request) {
        log.info("Enter in envoiTransfert [{}]", envoiTransfert);
        List<Pays> pays = paysService.findAll();
        Map<String, Pays> mapPays = new HashMap<>();
        for (Pays it : pays) {
            mapPays.put(it.getIsoAlpha2(), it);
        }
        EnvoiTransfertResponse genericResp = new EnvoiTransfertResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());
        RequestParam requestParam = requestParamService.findByPays(envoiTransfert.getTransaction().getPaysenvoi());
        if (requestParam == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.PARAMS_ADDITIONNELS_NON_TROUVES);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "envoiTransfert", genericResp.toString(),
                    envoiTransfert.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        // String autho = request.getHeader("Authorization");
        // String[] tab = autho.split("Bearer");
        ParamEndPoint endPoint = endPointService.findByCodeParam("envoiTransfert");
        if (endPoint == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "envoiTransfert", genericResp.toString(),
                    envoiTransfert.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            StringBuilder builder = new StringBuilder();
            builder.append("<envoietransaction>");
            builder.append("<channel_id>" + requestParam.getChannelId() + "</channel_id>");
            builder.append("<user_id>" + requestParam.getUserId() + "</user_id>");
            builder.append("<transaction_secret>" + requestParam.getTransactionSecret() + "</transaction_secret>");
            builder.append("<requestid>" + requestParam.getRequestId() + "</requestid>");
            builder.append("<codepartenaire>" + requestParam.getCodePartenaire() + "</codepartenaire>");

            Integer amount = envoiTransfert.getTransaction().getMontant().intValue();
            builder.append("<montant>" + amount + "</montant>");
            builder.append("<nom_emetteur>" + envoiTransfert.getTransaction().getSender().getNom() + "</nom_emetteur>");
            builder.append(
                    "<nom_recepteur>" + envoiTransfert.getTransaction().getBeneficiary().getNom() + "</nom_recepteur>");
            builder.append("<numerocompte_emetteur>" + envoiTransfert.getTransaction().getSender().getNumerocompte()
                    + "</numerocompte_emetteur>");
            builder.append("<paysdestination>"
                    + mapPays.get(envoiTransfert.getTransaction().getPaysdestination()).getIsoAlpha3()
                    + "</paysdestination>");
            builder.append("<paysenvoi>" + mapPays.get(envoiTransfert.getTransaction().getPaysenvoi()).getIsoAlpha3()
                    + "</paysenvoi>");
            builder.append("<prenom_emetteur>" + envoiTransfert.getTransaction().getSender().getPrenom()
                    + "</prenom_emetteur>");
            builder.append("<prenom_recepteur>" + envoiTransfert.getTransaction().getBeneficiary().getPrenom()
                    + "</prenom_recepteur>");
            builder.append(
                    "<questionsecrete>" + envoiTransfert.getTransaction().getQuestionsecrete() + "</questionsecrete>");
            String rt = "";
            for (int i = 0; i < envoiTransfert.getTransaction().getRaisontransfert().size(); i++) {
                if (i == 0)
                    rt += envoiTransfert.getTransaction().getRaisontransfert().get(i);
                else
                    rt = " - " + rt;
            }
            builder.append("<raisontransfert>" + rt + "</raisontransfert>");
            builder.append("<referencetransfert>" + envoiTransfert.getTransaction().getReferencebancaire()
                    + "</referencetransfert>");
            builder.append(
                    "<reponsesecrete>" + envoiTransfert.getTransaction().getReponsesecrete() + "</reponsesecrete>");
            builder.append("<telephoneport_emetteur>" + envoiTransfert.getTransaction().getSender().getTelephoneport()
                    + "</telephoneport_emetteur>");
            builder.append(
                    "<telephoneport_recepteur>" + envoiTransfert.getTransaction().getBeneficiary().getTelephoneport()
                            + "</telephoneport_recepteur>");
            builder.append(
                    "<typetransaction>" + envoiTransfert.getTransaction().getTypetransaction() + "</typetransaction>");
            builder.append("<nation_emet>"
                    + mapPays.get(envoiTransfert.getTransaction().getNationalite()).getIsoAlpha3() + "</nation_emet>");
            builder.append("<country>" + envoiTransfert.getTransaction().getPaysenvoi()+ "</country>");
            // builder.append("<ville_recepteur>"+envoiTransfert.getTransaction().getBeneficiary()+"</ville_recepteur>");
            // builder.append("<adresse_recepteur>"+envoiTransfert.getTransaction().getBeneficiary()+"</adresse_recepteur>");
            builder.append("</envoietransaction>");
            log.info("request envoi [{}]", builder.toString());
            HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), builder.toString(), "application/xml","");
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());
            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                obj = new JSONObject(result);
                if (obj.getJSONObject("deposertransactionresponse").toString().contains("numerotransaction")) {
                    obj = obj.getJSONObject("deposertransactionresponse");
                    com.boa.api.response.Transaction transaction = new com.boa.api.response.Transaction();
                    transaction.setAutretaxe1(obj.getJSONObject("transaction").getDouble("autretaxe1"));
                    transaction.setAutretaxe1(obj.getJSONObject("transaction").getDouble("autretaxe2"));
                    transaction.setAutretaxe1(obj.getJSONObject("transaction").getDouble("autretaxe3"));
                    transaction.setDatedepot(obj.getJSONObject("transaction").getString("datedepot"));
                    transaction.setDevisedestination(obj.getJSONObject("transaction").getString("devisedestination"));
                    transaction.setDeviseenvoi(obj.getJSONObject("transaction").getString("deviseenvoi"));
                    transaction.setFrais(obj.getJSONObject("transaction").getDouble("frais"));
                    transaction.setIdemetteur(obj.getJSONObject("transaction").getString("idemetteur"));
                    transaction.setIdrecepteur(obj.getJSONObject("transaction").getString("idrecepteur"));
                    transaction.setMontant(obj.getJSONObject("transaction").getDouble("montant"));
                    transaction.setMontantaupaiement(obj.getJSONObject("transaction").getDouble("montantaupaiement"));
                    transaction.setNumerotransaction(obj.getJSONObject("transaction").getString("numerotransaction"));
                    transaction.setPaysdestination(obj.getJSONObject("transaction").getString("paysdestination"));
                    transaction.setPaysenvoi(obj.getJSONObject("transaction").getString("paysenvoi"));
                    transaction.setTauxdechange(obj.getJSONObject("transaction").getDouble("tauxdechange"));
                    transaction.setTva(obj.getJSONObject("transaction").getDouble("tva"));
                    transaction.setTypetransaction(obj.getJSONObject("transaction").getInt("typetransaction"));
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    genericResp.setTransaction(transaction);
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), envoiTransfert.toString(), genericResp.getResponseReference());
                    createTransaction(envoiTransfert, genericResp.getTransaction().getNumerotransaction(),
                            transaction.getFrais());
                } else {
                    obj = obj.getJSONObject("deposertransactionresponse");
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), genericResp.toString(),
                            envoiTransfert.toString(), genericResp.getResponseReference());
                }

            } else {
                // conn =null || !=200
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), envoiTransfert.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in envoiTransfert [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
                    envoiTransfert.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    public CalculfraisTransfertResponse calculfraisTransfert(CalculfraisTransfertRequest calculfraisTransfert,
            HttpServletRequest request) {
        log.info("Enter in calculfraisTransfert [{}]", calculfraisTransfert);
        List<Pays> pays = paysService.findAll();
        Map<String, Pays> mapPays = new HashMap<>();
        for (Pays it : pays) {
            mapPays.put(it.getIsoAlpha2(), it);
        }
        CalculfraisTransfertResponse genericResp = new CalculfraisTransfertResponse();
        Tracking tracking = new Tracking();
        RequestParam requestParam = requestParamService.findByPays(calculfraisTransfert.getPaysenvoi());
        tracking.setDateRequest(Instant.now());
        if (requestParam == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.PARAMS_ADDITIONNELS_NON_TROUVES);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "calculfraisTransfert",
                    genericResp.toString(), calculfraisTransfert.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        // String autho = request.getHeader("Authorization");
        // String[] tab = autho.split("Bearer");
        ParamEndPoint endPoint = endPointService.findByCodeParam("calculfraisTransfert");
        if (endPoint == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "calculfraisTransfert",
                    genericResp.toString(), calculfraisTransfert.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            String jsonStr = new JSONObject().put("channel_id", requestParam.getChannelId())
                    .put("user_id", requestParam.getUserId())
                    .put("transaction_secret", requestParam.getTransactionSecret())
                    .put("requestid", requestParam.getRequestId())
                    .put("codepartenaire", requestParam.getCodePartenaire())
                    // .put("numerotransaction", calculfraisTransfert.get)
                    .put("montant", calculfraisTransfert.getMontant())
                    .put("paysenvoi", mapPays.get(calculfraisTransfert.getPaysenvoi()).getIsoAlpha3())
                    .put("paysdestination", mapPays.get(calculfraisTransfert.getPaysdestination()).getIsoAlpha3())
                    .put("typetransaction", calculfraisTransfert.getTypetransaction()).toString();
            log.info("req frais [{}]", jsonStr);
            HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), jsonStr, "application/json", "");
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());
            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                obj = new JSONObject(result);
                if (obj.get("getfraisettauxdechangeresponse").toString().contains("200")) {
                    obj = obj.getJSONObject("getfraisettauxdechangeresponse");
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    Fraistransaction frais = new Fraistransaction();
                    frais.autretaxe1(obj.getJSONObject("fraisetchange").getString("autretaxe1"))
                            .autretaxe2(obj.getJSONObject("fraisetchange").getString("autretaxe2"))
                            .autretaxe3(obj.getJSONObject("fraisetchange").getString("autretaxe3"))
                            .codepartenaire(obj.getJSONObject("fraisetchange").getString("codepartenaire"))
                            .devisedestination(obj.getJSONObject("fraisetchange").getString("devisedestination"))
                            .deviseenvoi(obj.getJSONObject("fraisetchange").getString("deviseenvoi"))
                            .frais(obj.getJSONObject("fraisetchange").getString("frais"))
                            .montant(obj.getJSONObject("fraisetchange").getString("montant"))
                            .paysdestination(obj.getJSONObject("fraisetchange").getString("paysdestination"))
                            .paysenvoi(obj.getJSONObject("fraisetchange").getString("paysenvoi"))
                            .tva(obj.getJSONObject("fraisetchange").getString("tva"))
                            .typetransaction(obj.getJSONObject("fraisetchange").getString("typetransaction"));
                    genericResp.setFraistransaction(frais);
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    genericResp.setDateResponse(Instant.now());
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), calculfraisTransfert.toString(),
                            genericResp.getResponseReference());
                } else if (obj.get("getfraisettauxdechangeresponse").toString().contains("detailsop")) {
                    obj = obj.getJSONObject("getfraisettauxdechangeresponse");
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), result,
                            calculfraisTransfert.toString(), genericResp.getResponseReference());
                }

            } else {
                // conn =null
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), calculfraisTransfert.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in calculfraisTransfert [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
            genericResp.setDetailsop(e.getMessage());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
                    calculfraisTransfert.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    public ConfirmTansfertResponse confirmTransfert(ConfirmTransfertRequest confirmReq, HttpServletRequest request) {
        log.info("Enter in confirmTransfert [{}]", confirmReq);
        ConfirmTansfertResponse genericResp = new ConfirmTansfertResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        // String autho = request.getHeader("Authorization");
        // String[] tab = autho.split("Bearer");
        ParamEndPoint endPoint = endPointService.findByCodeParam("confirmTransfert");
        if (endPoint == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "confirmTransfert",
                    genericResp.toString(), confirmReq.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            Transaction transaction = transactionService.findByNumeroTransaction(confirmReq.getNumerotransaction());
            if (transaction == null) {
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDescription(ICodeDescResponse.NUMERO_TRANSACTION_ABSENT);
                genericResp.setDateResponse(Instant.now());
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "confirmTransfert",
                        genericResp.toString(), confirmReq.toString(), genericResp.getResponseReference());
                trackingService.save(tracking);
                return genericResp;
            }
            RequestParam requestParam = requestParamService.findByPays(transaction.getPaysEnvoi());
            if (requestParam == null) {
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDescription(ICodeDescResponse.PARAMS_ADDITIONNELS_NON_TROUVES);
                genericResp.setDateResponse(Instant.now());
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "confirmTransfert",
                        genericResp.toString(), confirmReq.toString(), genericResp.getResponseReference());
                trackingService.save(tracking);
                return genericResp;
            }

            StringBuilder builder = new StringBuilder();
            builder.append("<confirmtransaction>");
            builder.append("<channel_id>" + requestParam.getChannelId() + "</channel_id>");
            builder.append("<user_id>" + requestParam.getUserId() + "</user_id>");
            builder.append("<transaction_secret>" + requestParam.getTransactionSecret() + "</transaction_secret>");
            builder.append("<requestid>" + requestParam.getRequestId() + "</requestid>");
            builder.append("<codepartenaire>" + requestParam.getCodePartenaire() + "</codepartenaire>");

            builder.append("<montant>" + transaction.getMontant().intValue() + "</montant>");
            builder.append("<referencetransfert>" + transaction.getReferenceBancaire() + "</referencetransfert>");
            builder.append("<numerotransaction>" + transaction.getNumeroTransaction() + "</numerotransaction>");
            builder.append("<compte_emetteur>" + transaction.getSenderCompte() + "</compte_emetteur>");
            builder.append("<disponible>" + "DISPONIBLE" + "</disponible>");
            builder.append("<valdisponible>" + "V" + "</valdisponible>");
            builder.append("<operation>" + "TRF" + "</operation>");
            builder.append("<country>" + transaction.getPaysEnvoi() + "</country>");
            builder.append("<mntfrais>" + transaction.getMontantFrais().intValue() + "</mntfrais>");
            builder.append("<libelle>" + transaction.getRaisonTransfert() + "</libelle>");
            builder.append("<compte_crediteur>" + "nil" + "</compte_crediteur>");
            builder.append("<codAuto>" + "nil" + "</codAuto>");
            builder.append("<paysdest>" + transaction.getPaysDestination() + "</paysdest>");
            
            builder.append("</confirmtransaction>");
            builder.append("</confirmtransaction>");
            log.info("request confirmation [{}]", builder.toString());
            HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), builder.toString(), "application/xml",
                    "");
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());
            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                obj = new JSONObject(result);
                if (obj.toString().contains("200")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    genericResp.setDateResponse(Instant.now());

                    obj = obj.getJSONObject("confirmationresponse");
                    genericResp.detailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                    Confirmdeposit confirmdeposit = new Confirmdeposit();
                    confirmdeposit.codepartenaire(obj.getJSONObject("details").getString("codepartenaire"))
                            .numerotransaction(obj.getJSONObject("details").getString("numerotransaction"))
                            .montant(obj.getJSONObject("details").getDouble("montant"))
                            .montantaupaiement(obj.getJSONObject("details").getDouble("montantaupaiement"))
                            .datetransaction(obj.getJSONObject("details").getString("datetransaction"))
                            .telephoneport(obj.getJSONObject("details").getString("telephoneport"));
                    genericResp.setConfirmdeposit(confirmdeposit);
                    confirmTransation(confirmReq.getNumerotransaction());
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), confirmReq.toString(), genericResp.getResponseReference());
                } else {
                    obj = obj.getJSONObject("confirmationresponse");
                    // Confirmdeposit confirmdeposit = new Confirmdeposit();
                    // confirmdeposit.
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), result,
                            confirmReq.toString(), genericResp.getResponseReference());
                }
            } else {
                // conn =null
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi error ===== [{}]", result);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), confirmReq.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in confirmTransfert [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
                    confirmReq.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    public TransactionByRefResponse getTransactionByReference(TransactionByRefRequest byRefRequest, HttpServletRequest request) {
        log.info("Enter in getTransactionByReference [{}]", byRefRequest);
        TransactionByRefResponse genericResp = new TransactionByRefResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        ParamEndPoint endPoint = endPointService.findByCodeParam("byRefRequest");
        if (endPoint == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "byRefRequest",
                    genericResp.toString(), byRefRequest.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            Transaction transaction = transactionService.findByNumeroReference(byRefRequest.getReferencebancaire());
            if (transaction == null) {
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDescription(ICodeDescResponse.NUMERO_TRANSACTION_ABSENT);
                genericResp.setDateResponse(Instant.now());
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "byRefRequest",
                        genericResp.toString(), byRefRequest.toString(), genericResp.getResponseReference());
                trackingService.save(tracking);
                return genericResp;
            }
            RequestParam requestParam = requestParamService.findByPays(transaction.getPaysEnvoi());
            if (requestParam == null) {
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDescription(ICodeDescResponse.PARAMS_ADDITIONNELS_NON_TROUVES);
                genericResp.setDateResponse(Instant.now());
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "byRefRequest",
                        genericResp.toString(), byRefRequest.toString(), genericResp.getResponseReference());
                trackingService.save(tracking);
                return genericResp;
            }

            String jsonStr = new JSONObject().put("channel_id", requestParam.getChannelId())
                    .put("user_id", requestParam.getUserId())
                    .put("transaction_secret", requestParam.getTransactionSecret())
                    .put("requestid", requestParam.getRequestId())
                    .put("codepartenaire", requestParam.getCodePartenaire())
                    .put("referencebancaire", byRefRequest.getReferencebancaire())
                    .put("sens", byRefRequest.getSens())
                    .toString();
            log.info("request confirmation [{}]", jsonStr);
            HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), jsonStr, "application/json",
                    "");
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());
            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                obj = new JSONObject(result);
                if (obj.toString().contains("200")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    genericResp.setDateResponse(Instant.now());

                    obj = obj.getJSONObject("gettransactionstatusbyreferenceresponse");
                    genericResp.detailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                   Status status = new Status();
                   status
                   .codepartenaire(obj.getJSONObject("statut").getString("codepartenaire"))
                   .statut(obj.getJSONObject("statut").getString("statut"))
                   .partenairereftrans(obj.getJSONObject("statut").getString("partenairereftrans"))
                   .numerotransaction(obj.getJSONObject("statut").getString("numerotransaction"));
                   genericResp.setStatus(status);
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), byRefRequest.toString(), genericResp.getResponseReference());
                } else {
                    // obj = obj.getJSONObject("xxx");
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), result,
                    byRefRequest.toString(), genericResp.getResponseReference());
                }
            } else {
                // conn =null
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi error ===== [{}]", result);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), byRefRequest.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in byRefRequest [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
            byRefRequest.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    public TransactionByNumResponse getTransactionByNum(TransactionByNumRequest byNumRequest, HttpServletRequest request) {
        log.info("Enter in getTransactionByNum [{}]", byNumRequest);
        TransactionByNumResponse genericResp = new TransactionByNumResponse();
        Tracking tracking = new Tracking();
        tracking.setDateRequest(Instant.now());

        ParamEndPoint endPoint = endPointService.findByCodeParam("byNumRequest");
        if (endPoint == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "byNumRequest",
                    genericResp.toString(), byNumRequest.toString(), genericResp.getResponseReference());
            trackingService.save(tracking);
            return genericResp;
        }
        try {
            Transaction transaction = transactionService.findByNumeroTransaction(byNumRequest.getNumerotransaction());
            if (transaction == null) {
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDescription(ICodeDescResponse.NUMERO_TRANSACTION_ABSENT);
                genericResp.setDateResponse(Instant.now());
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "byNumRequest",
                        genericResp.toString(), byNumRequest.toString(), genericResp.getResponseReference());
                trackingService.save(tracking);
                return genericResp;
            }
            RequestParam requestParam = requestParamService.findByPays(transaction.getPaysEnvoi());
            if (requestParam == null) {
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDescription(ICodeDescResponse.PARAMS_ADDITIONNELS_NON_TROUVES);
                genericResp.setDateResponse(Instant.now());
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "byNumRequest",
                        genericResp.toString(), byNumRequest.toString(), genericResp.getResponseReference());
                trackingService.save(tracking);
                return genericResp;
            }

            String jsonStr = new JSONObject().put("channel_id", requestParam.getChannelId())
                    .put("user_id", requestParam.getUserId())
                    .put("transaction_secret", requestParam.getTransactionSecret())
                    .put("requestid", requestParam.getRequestId())
                    .put("codepartenaire", requestParam.getCodePartenaire())
                    .put("numerotransaction", byNumRequest.getNumerotransaction())
                    .toString();
            log.info("request confirmation [{}]", jsonStr);
            HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), jsonStr, "application/json",
                    "");
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());
            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                obj = new JSONObject(result);
                if (obj.toString().contains("200")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    genericResp.setDateResponse(Instant.now());

                    obj = obj.getJSONObject("gettransactionstatusresponse");
                    Status status = new Status();
                   status
                   .codepartenaire(obj.getJSONObject("statut").getString("codepartenaire"))
                   .statut(obj.getJSONObject("statut").getString("statut"))
                   //.partenairereftrans(obj.getJSONObject("statut").getString("partenairereftrans"))
                   .numerotransaction(obj.getJSONObject("statut").getString("numerotransaction"));
                   genericResp.setStatus(status); 
                   genericResp.detailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                   
                    tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, request.getRequestURI(),
                            genericResp.toString(), byNumRequest.toString(), genericResp.getResponseReference());
                } else {
                    obj = obj.getJSONObject("gettransactionstatusresponse");
                    // Confirmdeposit confirmdeposit = new Confirmdeposit();
                    // confirmdeposit.
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), genericResp.toString(),
                    byNumRequest.toString(), genericResp.getResponseReference());
                }
            } else {
                // conn =null
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi error ===== [{}]", result);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(),
                        genericResp.toString(), byNumRequest.toString(), genericResp.getResponseReference());
            }
        } catch (Exception e) {
            log.error("Exception in byNumRequest [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
            tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, request.getRequestURI(), e.getMessage(),
            byNumRequest.toString(), genericResp.getResponseReference());
        }
        trackingService.save(tracking);
        return genericResp;
    }

    @Transactional(readOnly = true)
    public PaysActifsResponse getPaysActifs(String paysEnvoi) {
        log.info("Enter in getPaysActif ====== [{}]", paysEnvoi);
        List<Pays> pays = paysService.findAll();
        Map<String, Pays> mapPays = new HashMap<>();
        for (Pays it : pays) {
            mapPays.put(it.getIsoAlpha2(), it);
        }
        PaysActifsResponse genericResp = new PaysActifsResponse();

        ParamEndPoint endPoint = endPointService.findByCodeParam("paysActifs");
        if (endPoint == null) {
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDescription(ICodeDescResponse.SERVICE_ABSENT_DESC);
            genericResp.setDateResponse(Instant.now());
            //tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "paysActifs",
            //        genericResp.toString(), paysEnvoi, genericResp.getResponseReference());
            //trackingService.save(tracking);
            return genericResp;
        }
        try {
            RequestParam requestParam = requestParamService.findByPays(paysEnvoi);
            if (requestParam == null) {
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDescription(ICodeDescResponse.PARAMS_ADDITIONNELS_NON_TROUVES);
                genericResp.setDateResponse(Instant.now());
                //tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "paysActifs",
                  //      genericResp.toString(), paysEnvoi, genericResp.getResponseReference());
                //trackingService.save(tracking);
                return genericResp;
            }

            String jsonStr = new JSONObject().put("channel_id", requestParam.getChannelId())
                    .put("user_id", requestParam.getUserId())
                    .put("transaction_secret", requestParam.getTransactionSecret())
                    .put("requestid", requestParam.getRequestId())
                    .put("codepartenaire", requestParam.getCodePartenaire())
                    .toString();
            log.info("request confirmation [{}]", jsonStr);
            HttpURLConnection conn = utils.doConnexion(endPoint.getEndPoints(), jsonStr, "application/json",
                    "");
            BufferedReader br = null;
            JSONObject obj = new JSONObject();
            String result = "";
            log.info("resp code envoi [{}]", conn.getResponseCode());
            if (conn != null && conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi ===== [{}]", result);
                obj = new JSONObject(result);
                if (obj.toString().contains("200")) {
                    genericResp.setCode(ICodeDescResponse.SUCCES_CODE);
                    genericResp.setDescription(ICodeDescResponse.SUCCES_DESCRIPTION);
                    genericResp.setDateResponse(Instant.now());

                    obj = obj.getJSONObject("getboapaysresponse");
                    genericResp.detailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                    JSONArray array = obj.getJSONArray("pays");
                    if(array!=null && array.length()>0){
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject myObj = array.getJSONObject(i);
                            PaysResp paysResp = new PaysResp();
                            paysResp.code(myObj.getString("code"))
                            .nom(myObj.getString("nom"));
                            genericResp.getPays().add(paysResp);
                        }
                    }
                    //tracking = createTracking(tracking, ICodeDescResponse.SUCCES_CODE, "paysActifs",
                      //      genericResp.toString(), paysEnvoi, genericResp.getResponseReference());
                } else {
                    // obj = obj.getJSONObject("xxx");
                    genericResp.setDetailsop(obj.getString("detailsop"));
                    genericResp.setCodeop(obj.getString("codeop"));
                    genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                    genericResp.setDateResponse(Instant.now());
                    genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
                    //tracking = createTracking(tracking, ICodeDescResponse.ECHEC_CODE, "paysActifs", result,
                    //paysEnvoi, genericResp.getResponseReference());
                }
            } else {
                // conn =null
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("resp envoi error ===== [{}]", result);
                genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
                genericResp.setDateResponse(Instant.now());
                genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
            }
        } catch (Exception e) {
            log.error("Exception in pays Actifs [{}]", e);
            genericResp.setCode(ICodeDescResponse.ECHEC_CODE);
            genericResp.setDateResponse(Instant.now());
            genericResp.setDescription(ICodeDescResponse.ECHEC_DESCRIPTION);
        }
        return genericResp;
    }

    public Tracking createTracking(Tracking tracking, String code, String endPoint, String result, String req,
            String reqId) {
        // Tracking tracking = new Tracking();
        tracking.setCodeResponse(code);
        tracking.setDateResponse(Instant.now());
        tracking.setEndPoint(endPoint);
        tracking.setLoginActeur(userService.getUserWithAuthorities().get().getLogin());
        tracking.setResponseTr(result);
        tracking.setRequestTr(req);
        tracking.setRequestId(reqId);
        return tracking;
    }

    private Transaction createTransaction(EnvoiTransfertRequest transfertRequest, String numTransaction, Double frais) {
        log.info("Enter in createTransaction transfertRequest[{}], numTransac [{}]", transfertRequest, numTransaction);
        Transaction transaction = new Transaction();
        transaction.beneficiaryName(transfertRequest.getTransaction().getBeneficiary().getNom())
                .beneficiaryPhone(transfertRequest.getTransaction().getBeneficiary().getTelephoneport())
                .beneficiaryPrenom(transfertRequest.getTransaction().getBeneficiary().getPrenom())
                .canal(transfertRequest.getTransaction().getCanal())
                .montant(transfertRequest.getTransaction().getMontant())
                .paysEnvoi(transfertRequest.getTransaction().getPaysenvoi())
                .paysDestination(transfertRequest.getTransaction().getPaysdestination())
                .typeTransaction(transfertRequest.getTransaction().getTypetransaction().toString())
                .questionSecrete(transfertRequest.getTransaction().getQuestionsecrete())
                .reponseSecrete(transfertRequest.getTransaction().getReponsesecrete())
                .referenceBancaire(transfertRequest.getTransaction().getReferencebancaire())
                // .raisonTransfert(transfertRequest.getTransaction().getRaisontransfert())
                .senderCompte(transfertRequest.getTransaction().getSender().getNumerocompte())
                .senderName(transfertRequest.getTransaction().getSender().getNom())
                .senderPrenom(transfertRequest.getTransaction().getSender().getPrenom())
                .senderTelephone(transfertRequest.getTransaction().getSender().getTelephoneport())
                .numeroTransaction(numTransaction).dateCreated(Instant.now()).isConfirmed(false).montantFrais(frais);
        if (transfertRequest.getTransaction().getRaisontransfert() != null
                && !transfertRequest.getTransaction().getRaisontransfert().isEmpty()) {
            String res = "";
            for (String it : transfertRequest.getTransaction().getRaisontransfert()) {
                res += it;
            }
            transaction.raisonTransfert(res);
        }
        Transaction transactionSaved = transactionService.save(transaction);
        return transactionSaved;
    }

    private Transaction confirmTransation(String numTransaction) {
        log.info("Enter in confirmTransation numTransac [{}]", numTransaction);
        Transaction transaction = transactionService.findByNumeroTransaction(numTransaction);
        if (transaction != null && transaction.getId() != null) {
            transaction.isConfirmed(true).dateConfirmed(Instant.now());
        }
        return transaction;
    }

    @Transactional(readOnly = true)
    public Page<Pays> findPaysActifs(String paysEnvoi, Pageable pageable) {
        log.info("Request to get all Pays");
        List<Pays> pays = paysService.findAll();
        Map<String, Pays> mapPays = new HashMap<>();
        for (Pays it : pays) {
            mapPays.put(it.getIsoAlpha3(), it);
        }
        PaysActifsResponse resp = getPaysActifs(paysEnvoi);
        List<Pays> paysARetourner = new ArrayList<>();
        if(resp!=null && !resp.getPays().isEmpty()){
            for (PaysResp it : resp.getPays()) {
                Pays eachP = mapPays.get(it.getCode());
                paysARetourner.add(eachP);
            }
        }
 
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("pays"));
        //List<Pays> findAll = paysRepository.findAll(Sort.by("pays"));
        return new PageImpl<>(paysARetourner, pageable, pageable.getPageSize()); 
    }

}