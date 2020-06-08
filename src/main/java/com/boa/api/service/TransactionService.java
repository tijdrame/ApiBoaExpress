package com.boa.api.service;

import com.boa.api.domain.Transaction;
import com.boa.api.domain.UserExtra;
import com.boa.api.repository.TransactionRepository;
import com.boa.api.repository.UserExtraRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import liquibase.pro.packaged.p;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Transaction}.
 */
@Service
@Transactional
public class TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final UserExtraRepository userExtraRepository;
    private final UserService userService;

    public TransactionService(TransactionRepository transactionRepository, UserExtraRepository userExtraRepository,
    UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userExtraRepository = userExtraRepository;
        this.userService = userService;
    }

    /**
     * Save a transaction.
     *
     * @param transaction the entity to save.
     * @return the persisted entity.
     */
    public Transaction save(Transaction transaction) {
        log.info("Request to save Transaction : {}", transaction);
        return transactionRepository.save(transaction);
    }

    /**
     * Get all the transactions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Transaction> findAll(Pageable pageable) {
        log.info("Request to get all Transactions");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), 
        Sort.by(Sort.Direction.DESC, "dateConfirmed"));
        return transactionRepository.findAll(pageable);
        /*pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), 
        Sort.by(Sort.Direction.DESC, "dateConfirmed")); 
        return findByCriteria("%%", "%%", "%%", "%%", true, "%%", pageable);*/
    }

    @Transactional(readOnly = true)
    public Page<Transaction> findByCriteria(String refBancaire, String sendertel, String senderCompte, 
    String numTransation, Boolean isConfirmed, String paysEnvoi, Instant from, Instant to,
    String paysDestination, Pageable pageable) {
        log.info("Request to get Transactions By Criteria####");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), 
        Sort.by(Sort.Direction.DESC, "dateConfirmed")); 
        Optional<UserExtra> extra = userExtraRepository.findById(userService.getUserWithAuthorities().get().getId());
        String pays = "%%";
        if(extra.isPresent() /* &&paysEnvoi.length()>2 */ ){
            pays="%"+extra.get().getPays().getIsoAlpha2()+"%";
            paysEnvoi = pays;
        }
        
        return transactionRepository.findByCriterias
        (refBancaire, sendertel, senderCompte, numTransation, isConfirmed, paysEnvoi, 
        from, to, paysDestination, pageable);
    }


    /**
     * Get one transaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Transaction> findOne(Long id) {
        log.info("Request to get Transaction : {}", id);
        return transactionRepository.findById(id);
    }

    /**
     * Delete the transaction by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.info("Request to delete Transaction : {}", id);
        transactionRepository.deleteById(id);
    }

    Transaction findByNumeroTransaction(String numeroTransaction) {
        return transactionRepository.findByNumeroTransaction(numeroTransaction);
    }

    Transaction findByNumeroReference(String refence) {
        return transactionRepository.findByReferenceBancaire(refence);
    }

    public Long getTotalTransaction(Boolean status) {
        return transactionRepository.getTotalTransaction(status);
    }
    
    public Object getMontantAndFrais() {
        return transactionRepository.getMontantAndFrais();
    }

    public List<Object> getAmoutPerCountry() {
        return transactionRepository.getAmoutPerCountry();
    }
}
