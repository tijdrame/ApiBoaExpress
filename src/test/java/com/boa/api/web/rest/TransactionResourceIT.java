package com.boa.api.web.rest;

import com.boa.api.ApiBoaExpressApp;
import com.boa.api.domain.Transaction;
import com.boa.api.repository.TransactionRepository;
import com.boa.api.service.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TransactionResource} REST controller.
 */
@SpringBootTest(classes = ApiBoaExpressApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TransactionResourceIT {

    private static final Double DEFAULT_MONTANT = 1D;
    private static final Double UPDATED_MONTANT = 2D;

    private static final String DEFAULT_PAYS_ENVOI = "AAAAAAAAAA";
    private static final String UPDATED_PAYS_ENVOI = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS_DESTINATION = "AAAAAAAAAA";
    private static final String UPDATED_PAYS_DESTINATION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_TRANSACTION = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_TRANSACTION = "BBBBBBBBBB";

    private static final String DEFAULT_QUESTION_SECRETE = "AAAAAAAAAA";
    private static final String UPDATED_QUESTION_SECRETE = "BBBBBBBBBB";

    private static final String DEFAULT_REPONSE_SECRETE = "AAAAAAAAAA";
    private static final String UPDATED_REPONSE_SECRETE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_BANCAIRE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_BANCAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_RAISON_TRANSFERT = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_TRANSFERT = "BBBBBBBBBB";

    private static final String DEFAULT_CANAL = "AAAAAAAAAA";
    private static final String UPDATED_CANAL = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SENDER_COMPTE = "AAAAAAAAAA";
    private static final String UPDATED_SENDER_COMPTE = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_TRANSACTION = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_TRANSACTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_CONFIRMED = false;
    private static final Boolean UPDATED_IS_CONFIRMED = true;

    private static final String DEFAULT_CODE_CONFIM = "AAAAAAAAAA";
    private static final String UPDATED_CODE_CONFIM = "BBBBBBBBBB";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionMockMvc;

    private Transaction transaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .montant(DEFAULT_MONTANT)
            .paysEnvoi(DEFAULT_PAYS_ENVOI)
            .paysDestination(DEFAULT_PAYS_DESTINATION)
            .typeTransaction(DEFAULT_TYPE_TRANSACTION)
            .questionSecrete(DEFAULT_QUESTION_SECRETE)
            .reponseSecrete(DEFAULT_REPONSE_SECRETE)
            .referenceBancaire(DEFAULT_REFERENCE_BANCAIRE)
            .raisonTransfert(DEFAULT_RAISON_TRANSFERT)
            .canal(DEFAULT_CANAL)
            .senderName(DEFAULT_SENDER_NAME)
            .senderPrenom(DEFAULT_SENDER_PRENOM)
            .senderTelephone(DEFAULT_SENDER_TELEPHONE)
            .senderCompte(DEFAULT_SENDER_COMPTE)
            .beneficiaryName(DEFAULT_BENEFICIARY_NAME)
            .beneficiaryPrenom(DEFAULT_BENEFICIARY_PRENOM)
            .beneficiaryPhone(DEFAULT_BENEFICIARY_PHONE)
            .numeroTransaction(DEFAULT_NUMERO_TRANSACTION)
            .isConfirmed(DEFAULT_IS_CONFIRMED)
            .codeConfim(DEFAULT_CODE_CONFIM);
        return transaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createUpdatedEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .montant(UPDATED_MONTANT)
            .paysEnvoi(UPDATED_PAYS_ENVOI)
            .paysDestination(UPDATED_PAYS_DESTINATION)
            .typeTransaction(UPDATED_TYPE_TRANSACTION)
            .questionSecrete(UPDATED_QUESTION_SECRETE)
            .reponseSecrete(UPDATED_REPONSE_SECRETE)
            .referenceBancaire(UPDATED_REFERENCE_BANCAIRE)
            .raisonTransfert(UPDATED_RAISON_TRANSFERT)
            .canal(UPDATED_CANAL)
            .senderName(UPDATED_SENDER_NAME)
            .senderPrenom(UPDATED_SENDER_PRENOM)
            .senderTelephone(UPDATED_SENDER_TELEPHONE)
            .senderCompte(UPDATED_SENDER_COMPTE)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryPrenom(UPDATED_BENEFICIARY_PRENOM)
            .beneficiaryPhone(UPDATED_BENEFICIARY_PHONE)
            .numeroTransaction(UPDATED_NUMERO_TRANSACTION)
            .isConfirmed(UPDATED_IS_CONFIRMED)
            .codeConfim(UPDATED_CODE_CONFIM);
        return transaction;
    }

    @BeforeEach
    public void initTest() {
        transaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransaction() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();

        // Create the Transaction
        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isCreated());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate + 1);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testTransaction.getPaysEnvoi()).isEqualTo(DEFAULT_PAYS_ENVOI);
        assertThat(testTransaction.getPaysDestination()).isEqualTo(DEFAULT_PAYS_DESTINATION);
        assertThat(testTransaction.getTypeTransaction()).isEqualTo(DEFAULT_TYPE_TRANSACTION);
        assertThat(testTransaction.getQuestionSecrete()).isEqualTo(DEFAULT_QUESTION_SECRETE);
        assertThat(testTransaction.getReponseSecrete()).isEqualTo(DEFAULT_REPONSE_SECRETE);
        assertThat(testTransaction.getReferenceBancaire()).isEqualTo(DEFAULT_REFERENCE_BANCAIRE);
        assertThat(testTransaction.getRaisonTransfert()).isEqualTo(DEFAULT_RAISON_TRANSFERT);
        assertThat(testTransaction.getCanal()).isEqualTo(DEFAULT_CANAL);
        assertThat(testTransaction.getSenderName()).isEqualTo(DEFAULT_SENDER_NAME);
        assertThat(testTransaction.getSenderPrenom()).isEqualTo(DEFAULT_SENDER_PRENOM);
        assertThat(testTransaction.getSenderTelephone()).isEqualTo(DEFAULT_SENDER_TELEPHONE);
        assertThat(testTransaction.getSenderCompte()).isEqualTo(DEFAULT_SENDER_COMPTE);
        assertThat(testTransaction.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testTransaction.getBeneficiaryPrenom()).isEqualTo(DEFAULT_BENEFICIARY_PRENOM);
        assertThat(testTransaction.getBeneficiaryPhone()).isEqualTo(DEFAULT_BENEFICIARY_PHONE);
        assertThat(testTransaction.getNumeroTransaction()).isEqualTo(DEFAULT_NUMERO_TRANSACTION);
        assertThat(testTransaction.isIsConfirmed()).isEqualTo(DEFAULT_IS_CONFIRMED);
        assertThat(testTransaction.getCodeConfim()).isEqualTo(DEFAULT_CODE_CONFIM);
    }

    @Test
    @Transactional
    public void createTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();

        // Create the Transaction with an existing ID
        transaction.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setMontant(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaysEnvoiIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setPaysEnvoi(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaysDestinationIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setPaysDestination(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeTransactionIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setTypeTransaction(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuestionSecreteIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setQuestionSecrete(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReponseSecreteIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setReponseSecrete(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReferenceBancaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setReferenceBancaire(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSenderNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setSenderName(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSenderTelephoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setSenderTelephone(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSenderCompteIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setSenderCompte(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBeneficiaryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setBeneficiaryName(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBeneficiaryPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setBeneficiaryPhone(null);

        // Create the Transaction, which fails.

        restTransactionMockMvc.perform(post("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactions() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get all the transactionList
        restTransactionMockMvc.perform(get("/api/transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())))
            .andExpect(jsonPath("$.[*].paysEnvoi").value(hasItem(DEFAULT_PAYS_ENVOI)))
            .andExpect(jsonPath("$.[*].paysDestination").value(hasItem(DEFAULT_PAYS_DESTINATION)))
            .andExpect(jsonPath("$.[*].typeTransaction").value(hasItem(DEFAULT_TYPE_TRANSACTION)))
            .andExpect(jsonPath("$.[*].questionSecrete").value(hasItem(DEFAULT_QUESTION_SECRETE)))
            .andExpect(jsonPath("$.[*].reponseSecrete").value(hasItem(DEFAULT_REPONSE_SECRETE)))
            .andExpect(jsonPath("$.[*].referenceBancaire").value(hasItem(DEFAULT_REFERENCE_BANCAIRE)))
            .andExpect(jsonPath("$.[*].raisonTransfert").value(hasItem(DEFAULT_RAISON_TRANSFERT)))
            .andExpect(jsonPath("$.[*].canal").value(hasItem(DEFAULT_CANAL)))
            .andExpect(jsonPath("$.[*].senderName").value(hasItem(DEFAULT_SENDER_NAME)))
            .andExpect(jsonPath("$.[*].senderPrenom").value(hasItem(DEFAULT_SENDER_PRENOM)))
            .andExpect(jsonPath("$.[*].senderTelephone").value(hasItem(DEFAULT_SENDER_TELEPHONE)))
            .andExpect(jsonPath("$.[*].senderCompte").value(hasItem(DEFAULT_SENDER_COMPTE)))
            .andExpect(jsonPath("$.[*].beneficiaryName").value(hasItem(DEFAULT_BENEFICIARY_NAME)))
            .andExpect(jsonPath("$.[*].beneficiaryPrenom").value(hasItem(DEFAULT_BENEFICIARY_PRENOM)))
            .andExpect(jsonPath("$.[*].beneficiaryPhone").value(hasItem(DEFAULT_BENEFICIARY_PHONE)))
            .andExpect(jsonPath("$.[*].numeroTransaction").value(hasItem(DEFAULT_NUMERO_TRANSACTION)))
            .andExpect(jsonPath("$.[*].isConfirmed").value(hasItem(DEFAULT_IS_CONFIRMED.booleanValue())))
            .andExpect(jsonPath("$.[*].codeConfim").value(hasItem(DEFAULT_CODE_CONFIM)));
    }
    
    @Test
    @Transactional
    public void getTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get the transaction
        restTransactionMockMvc.perform(get("/api/transactions/{id}", transaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transaction.getId().intValue()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()))
            .andExpect(jsonPath("$.paysEnvoi").value(DEFAULT_PAYS_ENVOI))
            .andExpect(jsonPath("$.paysDestination").value(DEFAULT_PAYS_DESTINATION))
            .andExpect(jsonPath("$.typeTransaction").value(DEFAULT_TYPE_TRANSACTION))
            .andExpect(jsonPath("$.questionSecrete").value(DEFAULT_QUESTION_SECRETE))
            .andExpect(jsonPath("$.reponseSecrete").value(DEFAULT_REPONSE_SECRETE))
            .andExpect(jsonPath("$.referenceBancaire").value(DEFAULT_REFERENCE_BANCAIRE))
            .andExpect(jsonPath("$.raisonTransfert").value(DEFAULT_RAISON_TRANSFERT))
            .andExpect(jsonPath("$.canal").value(DEFAULT_CANAL))
            .andExpect(jsonPath("$.senderName").value(DEFAULT_SENDER_NAME))
            .andExpect(jsonPath("$.senderPrenom").value(DEFAULT_SENDER_PRENOM))
            .andExpect(jsonPath("$.senderTelephone").value(DEFAULT_SENDER_TELEPHONE))
            .andExpect(jsonPath("$.senderCompte").value(DEFAULT_SENDER_COMPTE))
            .andExpect(jsonPath("$.beneficiaryName").value(DEFAULT_BENEFICIARY_NAME))
            .andExpect(jsonPath("$.beneficiaryPrenom").value(DEFAULT_BENEFICIARY_PRENOM))
            .andExpect(jsonPath("$.beneficiaryPhone").value(DEFAULT_BENEFICIARY_PHONE))
            .andExpect(jsonPath("$.numeroTransaction").value(DEFAULT_NUMERO_TRANSACTION))
            .andExpect(jsonPath("$.isConfirmed").value(DEFAULT_IS_CONFIRMED.booleanValue()))
            .andExpect(jsonPath("$.codeConfim").value(DEFAULT_CODE_CONFIM));
    }

    @Test
    @Transactional
    public void getNonExistingTransaction() throws Exception {
        // Get the transaction
        restTransactionMockMvc.perform(get("/api/transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransaction() throws Exception {
        // Initialize the database
        transactionService.save(transaction);

        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Update the transaction
        Transaction updatedTransaction = transactionRepository.findById(transaction.getId()).get();
        // Disconnect from session so that the updates on updatedTransaction are not directly saved in db
        em.detach(updatedTransaction);
        updatedTransaction
            .montant(UPDATED_MONTANT)
            .paysEnvoi(UPDATED_PAYS_ENVOI)
            .paysDestination(UPDATED_PAYS_DESTINATION)
            .typeTransaction(UPDATED_TYPE_TRANSACTION)
            .questionSecrete(UPDATED_QUESTION_SECRETE)
            .reponseSecrete(UPDATED_REPONSE_SECRETE)
            .referenceBancaire(UPDATED_REFERENCE_BANCAIRE)
            .raisonTransfert(UPDATED_RAISON_TRANSFERT)
            .canal(UPDATED_CANAL)
            .senderName(UPDATED_SENDER_NAME)
            .senderPrenom(UPDATED_SENDER_PRENOM)
            .senderTelephone(UPDATED_SENDER_TELEPHONE)
            .senderCompte(UPDATED_SENDER_COMPTE)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryPrenom(UPDATED_BENEFICIARY_PRENOM)
            .beneficiaryPhone(UPDATED_BENEFICIARY_PHONE)
            .numeroTransaction(UPDATED_NUMERO_TRANSACTION)
            .isConfirmed(UPDATED_IS_CONFIRMED)
            .codeConfim(UPDATED_CODE_CONFIM);

        restTransactionMockMvc.perform(put("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTransaction)))
            .andExpect(status().isOk());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testTransaction.getPaysEnvoi()).isEqualTo(UPDATED_PAYS_ENVOI);
        assertThat(testTransaction.getPaysDestination()).isEqualTo(UPDATED_PAYS_DESTINATION);
        assertThat(testTransaction.getTypeTransaction()).isEqualTo(UPDATED_TYPE_TRANSACTION);
        assertThat(testTransaction.getQuestionSecrete()).isEqualTo(UPDATED_QUESTION_SECRETE);
        assertThat(testTransaction.getReponseSecrete()).isEqualTo(UPDATED_REPONSE_SECRETE);
        assertThat(testTransaction.getReferenceBancaire()).isEqualTo(UPDATED_REFERENCE_BANCAIRE);
        assertThat(testTransaction.getRaisonTransfert()).isEqualTo(UPDATED_RAISON_TRANSFERT);
        assertThat(testTransaction.getCanal()).isEqualTo(UPDATED_CANAL);
        assertThat(testTransaction.getSenderName()).isEqualTo(UPDATED_SENDER_NAME);
        assertThat(testTransaction.getSenderPrenom()).isEqualTo(UPDATED_SENDER_PRENOM);
        assertThat(testTransaction.getSenderTelephone()).isEqualTo(UPDATED_SENDER_TELEPHONE);
        assertThat(testTransaction.getSenderCompte()).isEqualTo(UPDATED_SENDER_COMPTE);
        assertThat(testTransaction.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testTransaction.getBeneficiaryPrenom()).isEqualTo(UPDATED_BENEFICIARY_PRENOM);
        assertThat(testTransaction.getBeneficiaryPhone()).isEqualTo(UPDATED_BENEFICIARY_PHONE);
        assertThat(testTransaction.getNumeroTransaction()).isEqualTo(UPDATED_NUMERO_TRANSACTION);
        assertThat(testTransaction.isIsConfirmed()).isEqualTo(UPDATED_IS_CONFIRMED);
        assertThat(testTransaction.getCodeConfim()).isEqualTo(UPDATED_CODE_CONFIM);
    }

    @Test
    @Transactional
    public void updateNonExistingTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Create the Transaction

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionMockMvc.perform(put("/api/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transaction)))
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransaction() throws Exception {
        // Initialize the database
        transactionService.save(transaction);

        int databaseSizeBeforeDelete = transactionRepository.findAll().size();

        // Delete the transaction
        restTransactionMockMvc.perform(delete("/api/transactions/{id}", transaction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
