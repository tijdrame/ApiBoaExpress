package com.boa.api.web.rest;

import com.boa.api.domain.Pays;
import com.boa.api.request.PaysActifsRequest;
import com.boa.api.response.PaysActifsResponse;
import com.boa.api.service.ApiService;
import com.boa.api.service.PaysService;
import com.boa.api.service.utils.ICodeDescResponse;
import com.boa.api.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import springfox.documentation.annotations.ApiIgnore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.boa.api.domain.Pays}.
 */
@RestController
@RequestMapping("/api")
@ApiIgnore
public class PaysResource {

    private final Logger log = LoggerFactory.getLogger(PaysResource.class);

    private final PaysService paysService;
    private final ApiService apiService;

    public PaysResource(PaysService paysService, ApiService apiService) {
        this.paysService = paysService;
        this.apiService = apiService;
    }

    /**
     * {@code GET  /pays} : get all the pays.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pays in body.
     */
    @GetMapping("/pays")
    public ResponseEntity<List<Pays>> getAllPays(Pageable pageable) {
        log.debug("REST request to get a page of Pays");
        Page<Pays> page = apiService.findPaysActifs("CI", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/paysBis")
    public ResponseEntity<List<Pays>> getAllPaysBis(Pageable pageable) {
        log.debug("REST request to get all Pays");
        List<Pays> page = paysService.findAll(); 
        //HttpHeaders headers = //PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/paysActifs")
    public ResponseEntity<PaysActifsResponse> getPaysActifs(
            @RequestBody PaysActifsRequest paysActifsRequest, HttpServletRequest request) {
        log.info("Request in getPaysActifs [{}]", paysActifsRequest);
        
        PaysActifsResponse response = new PaysActifsResponse();
        // doControl
        if(StringUtils.isEmpty(paysActifsRequest.getPaysEnvoi())
        ) {
            response.setCode(ICodeDescResponse.PARAM_ABSENT_CODE);
            response.setDateResponse(Instant.now());
            response.setDescription(ICodeDescResponse.PARAM_DESCRIPTION);
            return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization"))
                    .body(response);
        }
        response = apiService.getPaysActifs(paysActifsRequest.getPaysEnvoi());
        log.info("Response in byNumRequest [{}]", response);
        return ResponseEntity.ok().header("Authorization", request.getHeader("Authorization")).body(response);
    }

    /**
     * {@code GET  /pays/:id} : get the "id" pays.
     *
     * @param id the id of the pays to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pays, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pays/{id}")
    public ResponseEntity<Pays> getPays(@PathVariable Long id) {
        log.debug("REST request to get Pays : {}", id);
        Optional<Pays> pays = paysService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pays);
    }
}
