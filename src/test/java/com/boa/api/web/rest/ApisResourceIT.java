package com.boa.api.web.rest;

import com.boa.api.ApiBoaExpressApp;
import com.boa.api.service.ApiService;
import com.boa.api.service.utils.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Test class for the ApisResource REST controller.
 *
 * @see ApisResource
 */
@SpringBootTest(classes = ApiBoaExpressApp.class)
public class ApisResourceIT {

    private MockMvc restMockMvc;

    @Autowired
    private ApiService apiService;
    @Autowired
    private Utils utils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ApisResource apisResource = new ApisResource(apiService, utils);
        restMockMvc = MockMvcBuilders
            .standaloneSetup(apisResource)
            .build();
    }

    /**
     * Test defaultAction
     */
    @Test
    public void testDefaultAction() throws Exception {
        restMockMvc.perform(get("/api/apis-resource/default-action"))
            .andExpect(status().isOk());
    }
}
