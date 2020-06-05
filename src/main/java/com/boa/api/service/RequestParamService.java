package com.boa.api.service;

import com.boa.api.domain.RequestParam;
import com.boa.api.repository.RequestParamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RequestParam}.
 */
@Service
@Transactional
public class RequestParamService {

    private final Logger log = LoggerFactory.getLogger(RequestParamService.class);

    private final RequestParamRepository requestParamRepository;

    public RequestParamService(RequestParamRepository requestParamRepository) {
        this.requestParamRepository = requestParamRepository;
    }

    /**
     * Save a requestParam.
     *
     * @param requestParam the entity to save.
     * @return the persisted entity.
     */
    public RequestParam save(RequestParam requestParam) {
        log.info("Request to save RequestParam : {}", requestParam);
        return requestParamRepository.save(requestParam);
    }

    /**
     * Get all the requestParams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestParam> findAll(Pageable pageable) {
        log.info("Request to get all RequestParams");
        return requestParamRepository.findAll(pageable);
    }

    /**
     * Get one requestParam by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RequestParam> findOne(Long id) {
        log.info("Request to get RequestParam : {}", id);
        return requestParamRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public RequestParam findByPays(String pays) {
        log.info("Request to get RequestParam : {}", pays);
        return requestParamRepository.findByPays(pays);
    }

    /**
     * Delete the requestParam by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.info("Request to delete RequestParam : {}", id);
        requestParamRepository.deleteById(id);
    }
}
