package com.boa.api.web.rest;

import com.boa.api.ApiBoaExpressApp;
import com.boa.api.domain.RequestParam;
import com.boa.api.repository.RequestParamRepository;
import com.boa.api.service.RequestParamService;

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
 * Integration tests for the {@link RequestParamResource} REST controller.
 */
@SpringBootTest(classes = ApiBoaExpressApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RequestParamResourceIT {

    private static final String DEFAULT_CHANNEL_ID = "AAAAAAAAAA";
    private static final String UPDATED_CHANNEL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_SECRET = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_SECRET = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_PARTENAIRE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PARTENAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    @Autowired
    private RequestParamRepository requestParamRepository;

    @Autowired
    private RequestParamService requestParamService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestParamMockMvc;

    private RequestParam requestParam;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestParam createEntity(EntityManager em) {
        RequestParam requestParam = new RequestParam()
            .channelId(DEFAULT_CHANNEL_ID)
            .userId(DEFAULT_USER_ID)
            .transactionSecret(DEFAULT_TRANSACTION_SECRET)
            .requestId(DEFAULT_REQUEST_ID)
            .codePartenaire(DEFAULT_CODE_PARTENAIRE)
            .pays(DEFAULT_PAYS);
        return requestParam;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestParam createUpdatedEntity(EntityManager em) {
        RequestParam requestParam = new RequestParam()
            .channelId(UPDATED_CHANNEL_ID)
            .userId(UPDATED_USER_ID)
            .transactionSecret(UPDATED_TRANSACTION_SECRET)
            .requestId(UPDATED_REQUEST_ID)
            .codePartenaire(UPDATED_CODE_PARTENAIRE)
            .pays(UPDATED_PAYS);
        return requestParam;
    }

    @BeforeEach
    public void initTest() {
        requestParam = createEntity(em);
    }

    @Test
    @Transactional
    public void createRequestParam() throws Exception {
        int databaseSizeBeforeCreate = requestParamRepository.findAll().size();

        // Create the RequestParam
        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isCreated());

        // Validate the RequestParam in the database
        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeCreate + 1);
        RequestParam testRequestParam = requestParamList.get(requestParamList.size() - 1);
        assertThat(testRequestParam.getChannelId()).isEqualTo(DEFAULT_CHANNEL_ID);
        assertThat(testRequestParam.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testRequestParam.getTransactionSecret()).isEqualTo(DEFAULT_TRANSACTION_SECRET);
        assertThat(testRequestParam.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testRequestParam.getCodePartenaire()).isEqualTo(DEFAULT_CODE_PARTENAIRE);
        assertThat(testRequestParam.getPays()).isEqualTo(DEFAULT_PAYS);
    }

    @Test
    @Transactional
    public void createRequestParamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = requestParamRepository.findAll().size();

        // Create the RequestParam with an existing ID
        requestParam.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        // Validate the RequestParam in the database
        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkChannelIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestParamRepository.findAll().size();
        // set the field null
        requestParam.setChannelId(null);

        // Create the RequestParam, which fails.

        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestParamRepository.findAll().size();
        // set the field null
        requestParam.setUserId(null);

        // Create the RequestParam, which fails.

        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransactionSecretIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestParamRepository.findAll().size();
        // set the field null
        requestParam.setTransactionSecret(null);

        // Create the RequestParam, which fails.

        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRequestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestParamRepository.findAll().size();
        // set the field null
        requestParam.setRequestId(null);

        // Create the RequestParam, which fails.

        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodePartenaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestParamRepository.findAll().size();
        // set the field null
        requestParam.setCodePartenaire(null);

        // Create the RequestParam, which fails.

        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestParamRepository.findAll().size();
        // set the field null
        requestParam.setPays(null);

        // Create the RequestParam, which fails.

        restRequestParamMockMvc.perform(post("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRequestParams() throws Exception {
        // Initialize the database
        requestParamRepository.saveAndFlush(requestParam);

        // Get all the requestParamList
        restRequestParamMockMvc.perform(get("/api/request-params?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].channelId").value(hasItem(DEFAULT_CHANNEL_ID)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].transactionSecret").value(hasItem(DEFAULT_TRANSACTION_SECRET)))
            .andExpect(jsonPath("$.[*].requestId").value(hasItem(DEFAULT_REQUEST_ID)))
            .andExpect(jsonPath("$.[*].codePartenaire").value(hasItem(DEFAULT_CODE_PARTENAIRE)))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS)));
    }
    
    @Test
    @Transactional
    public void getRequestParam() throws Exception {
        // Initialize the database
        requestParamRepository.saveAndFlush(requestParam);

        // Get the requestParam
        restRequestParamMockMvc.perform(get("/api/request-params/{id}", requestParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestParam.getId().intValue()))
            .andExpect(jsonPath("$.channelId").value(DEFAULT_CHANNEL_ID))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.transactionSecret").value(DEFAULT_TRANSACTION_SECRET))
            .andExpect(jsonPath("$.requestId").value(DEFAULT_REQUEST_ID))
            .andExpect(jsonPath("$.codePartenaire").value(DEFAULT_CODE_PARTENAIRE))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS));
    }

    @Test
    @Transactional
    public void getNonExistingRequestParam() throws Exception {
        // Get the requestParam
        restRequestParamMockMvc.perform(get("/api/request-params/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestParam() throws Exception {
        // Initialize the database
        requestParamService.save(requestParam);

        int databaseSizeBeforeUpdate = requestParamRepository.findAll().size();

        // Update the requestParam
        RequestParam updatedRequestParam = requestParamRepository.findById(requestParam.getId()).get();
        // Disconnect from session so that the updates on updatedRequestParam are not directly saved in db
        em.detach(updatedRequestParam);
        updatedRequestParam
            .channelId(UPDATED_CHANNEL_ID)
            .userId(UPDATED_USER_ID)
            .transactionSecret(UPDATED_TRANSACTION_SECRET)
            .requestId(UPDATED_REQUEST_ID)
            .codePartenaire(UPDATED_CODE_PARTENAIRE)
            .pays(UPDATED_PAYS);

        restRequestParamMockMvc.perform(put("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRequestParam)))
            .andExpect(status().isOk());

        // Validate the RequestParam in the database
        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeUpdate);
        RequestParam testRequestParam = requestParamList.get(requestParamList.size() - 1);
        assertThat(testRequestParam.getChannelId()).isEqualTo(UPDATED_CHANNEL_ID);
        assertThat(testRequestParam.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testRequestParam.getTransactionSecret()).isEqualTo(UPDATED_TRANSACTION_SECRET);
        assertThat(testRequestParam.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testRequestParam.getCodePartenaire()).isEqualTo(UPDATED_CODE_PARTENAIRE);
        assertThat(testRequestParam.getPays()).isEqualTo(UPDATED_PAYS);
    }

    @Test
    @Transactional
    public void updateNonExistingRequestParam() throws Exception {
        int databaseSizeBeforeUpdate = requestParamRepository.findAll().size();

        // Create the RequestParam

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestParamMockMvc.perform(put("/api/request-params")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(requestParam)))
            .andExpect(status().isBadRequest());

        // Validate the RequestParam in the database
        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRequestParam() throws Exception {
        // Initialize the database
        requestParamService.save(requestParam);

        int databaseSizeBeforeDelete = requestParamRepository.findAll().size();

        // Delete the requestParam
        restRequestParamMockMvc.perform(delete("/api/request-params/{id}", requestParam.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestParam> requestParamList = requestParamRepository.findAll();
        assertThat(requestParamList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
