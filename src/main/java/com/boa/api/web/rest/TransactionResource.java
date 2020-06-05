package com.boa.api.web.rest;

import com.boa.api.domain.Transaction;
import com.boa.api.response.MontantTransactionResponse;
import com.boa.api.response.TotalTransactionResponse;
import com.boa.api.service.TransactionService;
import com.boa.api.web.rest.errors.BadRequestAlertException;

import springfox.documentation.annotations.ApiIgnore;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.boa.api.domain.Transaction}.
 */
@RestController
@RequestMapping("/api")
@ApiIgnore
public class TransactionResource {

    private final Logger log = LoggerFactory.getLogger(TransactionResource.class);

    private static final String ENTITY_NAME = "transaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransactionService transactionService;

    public TransactionResource(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * {@code POST  /transactions} : Create a new transaction.
     *
     * @param transaction the transaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transaction, or with status {@code 400 (Bad Request)} if the transaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) throws URISyntaxException {
        log.debug("REST request to save Transaction : {}", transaction);
        if (transaction.getId() != null) {
            throw new BadRequestAlertException("A new transaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Transaction result = transactionService.save(transaction);
        return ResponseEntity.created(new URI("/api/transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transactions} : Updates an existing transaction.
     *
     * @param transaction the transaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transaction,
     * or with status {@code 400 (Bad Request)} if the transaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transactions")
    public ResponseEntity<Transaction> updateTransaction(@Valid @RequestBody Transaction transaction) throws URISyntaxException {
        log.info("REST request to update Transaction : {}", transaction);
        if (transaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Transaction result = transactionService.save(transaction);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transaction.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transactions} : get all the transactions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transactions in body.
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(Pageable pageable) {
        log.info("REST request to get a page of Transactions");
        Page<Transaction> page = transactionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping(path = "/transactionsBis",
        params = {"refBancaire", "sendertel", "senderCompte", "numTransation", "dateDebut", "dateFin","paysEnvoi", "paysDestination"})
    public ResponseEntity<List<Transaction>> getAllTransactions(@RequestParam Optional<String> refBancaire,
    @RequestParam Optional<String> sendertel, @RequestParam Optional<String> senderCompte, 
    @RequestParam Optional<String> numTransation, @RequestParam LocalDate dateDebut,
    @RequestParam LocalDate dateFin, @RequestParam Optional<String> paysEnvoi,
    @RequestParam Optional<String> paysDestination, Pageable pageable) {
        log.info("REST request to get a page of Transactions");
        log.debug("Ref Bancaire [{}]",refBancaire.isPresent()?"%"+refBancaire.get().trim()+"%":"");
        log.debug("sendertel [{}]",sendertel.isPresent()?"%"+sendertel.get().trim()+"%":"");
        log.debug("senderCompte [{}]",senderCompte.isPresent()?"%"+senderCompte.get().trim()+"%":"");
        log.debug("numTransation [{}]",numTransation.isPresent()?"%"+numTransation.get().trim()+"%":"");
        
        LocalDateTime fromB = dateDebut.atTime(LocalTime.MIN);
        LocalDateTime toB = dateFin.atTime(LocalTime.MAX);
        Instant from = fromB.atZone(ZoneId.systemDefault()).toInstant();;
        Instant to = toB.atZone(ZoneId.systemDefault()).toInstant();
        
        Page<Transaction> page = transactionService.findByCriteria(
            refBancaire.isPresent()?"%"+refBancaire.get().trim()+"%":"", 
            sendertel.isPresent()?"%"+sendertel.get().trim()+"%":"", 
            senderCompte.isPresent()?"%"+senderCompte.get().trim()+"%":"", 
            numTransation.isPresent()?"%"+numTransation.get().trim()+"%":"", true,
            paysEnvoi.isPresent()?"%"+paysEnvoi.get().trim()+"%":"", from, to,
            paysDestination.isPresent()?"%"+paysDestination.get().trim()+"%":"",pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /transactions/:id} : get the "id" transaction.
     *
     * @param id the id of the transaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        log.debug("REST request to get Transaction : {}", id);
        Optional<Transaction> transaction = transactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transaction);
    }

    /**
     * {@code DELETE  /transactions/:id} : delete the "id" transaction.
     *
     * @param id the id of the transaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        log.debug("REST request to delete Transaction : {}", id);
        transactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping(path = "/transactionsNumber")
    public ResponseEntity<TotalTransactionResponse>getTotalTransaction() {
        Long totConf = transactionService.getTotalTransaction(true);
        Long totGen = transactionService.getTotalTransaction(false);
        TotalTransactionResponse response = new TotalTransactionResponse();
        response.setCode("200");
        response.setDescription("Operation effectuee avec success");
        response.setDateResponse(Instant.now());
        response.setTotalConfirme(totConf);
        response.setTotalGeneral(totGen);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/transactionsMontant")
    public ResponseEntity<MontantTransactionResponse>getMontantTransaction() {
        Object myObject = transactionService.getMontantAndFrais();
        log.info("myObj [{}]",myObject);
        MontantTransactionResponse response = new MontantTransactionResponse();
        if(myObject!=null){
            Object[] arrayRefVar =  (Object[]) myObject;
            log.info("myObj [{}]",arrayRefVar[0]);
            response.setMontant((Double)arrayRefVar[0]);
            response.setMontantFrais((Double)arrayRefVar[1]);
        }
        
        response.setCode("200");
        response.setDescription("Operation effectuee avec success");
        response.setDateResponse(Instant.now());
        
        return ResponseEntity.ok().body(response);
    }
}
