package com.boa.api.web.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.boa.api.request.CalculfraisTransfertRequest;
import com.boa.api.request.ConfirmTransfertRequest;
import com.boa.api.request.EnvoiTransfertRequest;
import com.boa.api.request.PaysActifsRequest;
import com.boa.api.request.TransactionByNumRequest;
import com.boa.api.request.TransactionByRefRequest;
import com.boa.api.response.CalculfraisTransfertResponse;
import com.boa.api.response.ConfirmTansfertResponse;
import com.boa.api.response.EnvoiTransfertResponse;
import com.boa.api.response.GenericResponse;
import com.boa.api.response.PaysActifsResponse;
import com.boa.api.response.TransactionByNumResponse;
import com.boa.api.response.TransactionByRefResponse;
import com.boa.api.service.ApiService;
import com.boa.api.service.utils.ICodeDescResponse;
import com.boa.api.service.utils.Utils;
import com.boa.api.web.rest.vm.LoginVM;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

/**
 * ApisResource controller
 */
@RestController
@RequestMapping("/api")
public class ApisResource {

    private final Logger log = LoggerFactory.getLogger(ApisResource.class);

    private final ApiService apiService;
    private final Utils utils;

    public ApisResource(ApiService apiService, Utils utils) {
        this.apiService = apiService;
        this.utils = utils;
    }

    @PostMapping("/envoiTransfert") 
    public ResponseEntity<EnvoiTransfertResponse> envoiTransfert(@RequestBody EnvoiTransfertRequest envoiTransfert,
            HttpServletRequest request) {
        log.info("Enter in envoiTransfert [{}]", envoiTransfert);
        EnvoiTransfertResponse response = new EnvoiTransfertResponse();
        // doControl
        if(StringUtils.isEmpty(envoiTransfert.getTransaction().getPaysenvoi())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getPaysdestination())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getTypetransaction().toString())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getReferencebancaire())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getSender().getNom())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getSender().getPrenom())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getSender().getTelephoneport())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getSender().getNumerocompte())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getBeneficiary().getNom())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getBeneficiary().getPrenom())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getBeneficiary().getTelephoneport())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getMontant().toString())
        ) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        if(envoiTransfert.getTransaction().getTypetransaction()==1&&
        (StringUtils.isEmpty(envoiTransfert.getTransaction().getReponsesecrete())||
        StringUtils.isEmpty(envoiTransfert.getTransaction().getQuestionsecrete()))
        ){
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        response = apiService.envoiTransfert(envoiTransfert, request);
        log.info("Response in envoiTransfert [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/confirmTransfert")
    public ResponseEntity<ConfirmTansfertResponse> confirmTransfert(@RequestBody ConfirmTransfertRequest confirmReq,
            HttpServletRequest request) {
        log.info("Enter in confirmTransfert [{}]", confirmReq);
        ConfirmTansfertResponse response = new ConfirmTansfertResponse();
        // doControl
        if(StringUtils.isEmpty(confirmReq.getNumerotransaction())||
        StringUtils.isEmpty(confirmReq.getReferencebancaire())) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        response = apiService.confirmTransfert(confirmReq, request);
        log.info("Enter in confirmTransfert [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/calculfraisTransfert")
    public ResponseEntity<CalculfraisTransfertResponse> calculfraisTransfert(
            @RequestBody CalculfraisTransfertRequest calculfraisTransfert, HttpServletRequest request) {
        log.info("Request in calculfraisTransfert [{}]", calculfraisTransfert);
        
        CalculfraisTransfertResponse response = new CalculfraisTransfertResponse();
        // doControl
        if(StringUtils.isEmpty(calculfraisTransfert.getMontant().toString())||
        StringUtils.isEmpty(calculfraisTransfert.getPaysdestination())||
        StringUtils.isEmpty(calculfraisTransfert.getPaysenvoi())||
        StringUtils.isEmpty(calculfraisTransfert.getTypetransaction().toString())
        ) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        response = apiService.calculfraisTransfert(calculfraisTransfert, request);
        log.info("Response in calculfraisTransfert [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/statusTransactionByReference")
    public ResponseEntity<TransactionByRefResponse> transactionByRef(
            @RequestBody TransactionByRefRequest byRefRequest, HttpServletRequest request) {
        log.info("Request in transactionByRef [{}]", byRefRequest);
        
        TransactionByRefResponse response = new TransactionByRefResponse();
        // doControl
        if(StringUtils.isEmpty(byRefRequest.getReferencebancaire())||
        StringUtils.isEmpty(byRefRequest.getSens())
        ) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        response = apiService.getTransactionByReference(byRefRequest, request);
        log.info("Response in transactionByRef [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/statusTransactionByNumber")
    public ResponseEntity<TransactionByNumResponse> transactionByNum(
            @RequestBody TransactionByNumRequest byNumRequest, HttpServletRequest request) {
        log.info("Request in byNumRequest [{}]", byNumRequest);
        
        TransactionByNumResponse response = new TransactionByNumResponse();
        // doControl
        if(StringUtils.isEmpty(byNumRequest.getNumerotransaction())
        ) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        response = apiService.getTransactionByNum(byNumRequest, request);
        log.info("Response in byNumRequest [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    @PostMapping("/testApi")
    @ApiIgnore
    public ResponseEntity<GenericResponse> testWebClient(@RequestBody GenericResponse gen, HttpServletRequest request) {
        // CheckFactoryRequest checkFactoryRequest = new CheckFactoryRequest();
        /*
         * webClient.build().post().uri("http://localhost:8888/checkFactCnss")
         * .bodyValue(checkFactoryRequest) .retrieve()
         * .bodyToMono(CheckFactoryRequest.class) .block();
         */
        // Map<String, String> uriVariables = new HashMap<>();
        // uriVariables.put("key", value)
        GenericResponse response = new GenericResponse();
        response.setCode("200");
        response.description("OK");
        log.info("resp ref [{}] & resp [{}]", response.getResponseReference(), response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);

    }

    @PostMapping("/testTemplate")
    @ApiIgnore
    public ResponseEntity<GenericResponse> testTemplate(@RequestBody LoginVM loginVM, HttpServletRequest request)
            throws JSONException {
        HttpURLConnection conn;
        try {
            String jsonReq = new JSONObject().put("username", loginVM.getUsername())
                    .put("password", loginVM.getPassword()).toString();
            conn = utils.doConnexion("http://localhost:8080/api/authenticate", jsonReq, "application/json",
                    "application/json");
            log.info("msg [{}]", conn.getResponseCode());
            BufferedReader br = null;
            String result = "";
            if (conn.getResponseCode() == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("result [{}]", result);
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String ligne = br.readLine();
                while (ligne != null) {
                    result += ligne;
                    ligne = br.readLine();
                }
                log.info("result [{}]", result);
            }
            conn.disconnect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        GenericResponse response = new GenericResponse();
        response.setCode("200");
        response.description("OK");
        log.info("resp ref [{}] & resp [{}]", response.getResponseReference(), response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);

    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        GenericResponse r = new GenericResponse();
        r.code("200").description("xxxx").dateResponse(Instant.now());
        String json = mapper.writeValueAsString(r);
        System.out.println("r"+json);
    }

}
