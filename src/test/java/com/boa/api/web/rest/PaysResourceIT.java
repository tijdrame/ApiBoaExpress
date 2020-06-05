package com.boa.api.web.rest;

import com.boa.api.ApiBoaExpressApp;
import com.boa.api.domain.Pays;
import com.boa.api.repository.PaysRepository;
import com.boa.api.service.PaysService;

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
 * Integration tests for the {@link PaysResource} REST controller.
 */
@SpringBootTest(classes = ApiBoaExpressApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PaysResourceIT {

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    private static final String DEFAULT_ISO_ALPHA_2 = "AAAAAAAAAA";
    private static final String UPDATED_ISO_ALPHA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ISO_ALPHA_3 = "AAAAAAAAAA";
    private static final String UPDATED_ISO_ALPHA_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ISO_NUMERIQUE = "AAAAAAAAAA";
    private static final String UPDATED_ISO_NUMERIQUE = "BBBBBBBBBB";

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private PaysService paysService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaysMockMvc;

    private Pays pays;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pays createEntity(EntityManager em) {
        Pays pays = new Pays()
            .pays(DEFAULT_PAYS)
            .isoAlpha2(DEFAULT_ISO_ALPHA_2)
            .isoAlpha3(DEFAULT_ISO_ALPHA_3)
            .isoNumerique(DEFAULT_ISO_NUMERIQUE);
        return pays;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pays createUpdatedEntity(EntityManager em) {
        Pays pays = new Pays()
            .pays(UPDATED_PAYS)
            .isoAlpha2(UPDATED_ISO_ALPHA_2)
            .isoAlpha3(UPDATED_ISO_ALPHA_3)
            .isoNumerique(UPDATED_ISO_NUMERIQUE);
        return pays;
    }

    @BeforeEach
    public void initTest() {
        pays = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllPays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        // Get all the paysList
        restPaysMockMvc.perform(get("/api/pays?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pays.getId().intValue())))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS)))
            .andExpect(jsonPath("$.[*].isoAlpha2").value(hasItem(DEFAULT_ISO_ALPHA_2)))
            .andExpect(jsonPath("$.[*].isoAlpha3").value(hasItem(DEFAULT_ISO_ALPHA_3)))
            .andExpect(jsonPath("$.[*].isoNumerique").value(hasItem(DEFAULT_ISO_NUMERIQUE)));
    }
    
    @Test
    @Transactional
    public void getPays() throws Exception {
        // Initialize the database
        paysRepository.saveAndFlush(pays);

        // Get the pays
        restPaysMockMvc.perform(get("/api/pays/{id}", pays.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pays.getId().intValue()))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS))
            .andExpect(jsonPath("$.isoAlpha2").value(DEFAULT_ISO_ALPHA_2))
            .andExpect(jsonPath("$.isoAlpha3").value(DEFAULT_ISO_ALPHA_3))
            .andExpect(jsonPath("$.isoNumerique").value(DEFAULT_ISO_NUMERIQUE));
    }

    @Test
    @Transactional
    public void getNonExistingPays() throws Exception {
        // Get the pays
        restPaysMockMvc.perform(get("/api/pays/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
