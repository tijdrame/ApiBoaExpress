package com.boa.api.web.rest;

import com.boa.api.domain.RequestParam;
import com.boa.api.service.RequestParamService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.boa.api.domain.RequestParam}.
 */
@RestController
@RequestMapping("/api")
@ApiIgnore
public class RequestParamResource {

    private final Logger log = LoggerFactory.getLogger(RequestParamResource.class);

    private static final String ENTITY_NAME = "requestParam";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestParamService requestParamService;

    public RequestParamResource(RequestParamService requestParamService) {
        this.requestParamService = requestParamService;
    }

    /**
     * {@code POST  /request-params} : Create a new requestParam.
     *
     * @param requestParam the requestParam to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestParam, or with status {@code 400 (Bad Request)} if the requestParam has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-params")
    public ResponseEntity<RequestParam> createRequestParam(@Valid @RequestBody RequestParam requestParam) throws URISyntaxException {
        log.debug("REST request to save RequestParam : {}", requestParam);
        if (requestParam.getId() != null) {
            throw new BadRequestAlertException("A new requestParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RequestParam result = requestParamService.save(requestParam);
        return ResponseEntity.created(new URI("/api/request-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /request-params} : Updates an existing requestParam.
     *
     * @param requestParam the requestParam to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestParam,
     * or with status {@code 400 (Bad Request)} if the requestParam is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestParam couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-params")
    public ResponseEntity<RequestParam> updateRequestParam(@Valid @RequestBody RequestParam requestParam) throws URISyntaxException {
        log.debug("REST request to update RequestParam : {}", requestParam);
        if (requestParam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RequestParam result = requestParamService.save(requestParam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, requestParam.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /request-params} : get all the requestParams.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestParams in body.
     */
    @GetMapping("/request-params")
    public ResponseEntity<List<RequestParam>> getAllRequestParams(Pageable pageable) {
        log.debug("REST request to get a page of RequestParams");
        Page<RequestParam> page = requestParamService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /request-params/:id} : get the "id" requestParam.
     *
     * @param id the id of the requestParam to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestParam, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-params/{id}")
    public ResponseEntity<RequestParam> getRequestParam(@PathVariable Long id) {
        log.debug("REST request to get RequestParam : {}", id);
        Optional<RequestParam> requestParam = requestParamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestParam);
    }

    /**
     * {@code DELETE  /request-params/:id} : delete the "id" requestParam.
     *
     * @param id the id of the requestParam to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-params/{id}")
    public ResponseEntity<Void> deleteRequestParam(@PathVariable Long id) {
        log.debug("REST request to delete RequestParam : {}", id);
        requestParamService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
