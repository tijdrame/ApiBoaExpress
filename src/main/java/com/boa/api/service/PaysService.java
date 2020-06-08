package com.boa.api.service;

import com.boa.api.domain.Pays;
import com.boa.api.repository.PaysRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Pays}.
 */
@Service
@Transactional
public class PaysService {

    private final Logger log = LoggerFactory.getLogger(PaysService.class);

    private final PaysRepository paysRepository;

    public PaysService(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    /**
     * Save a pays.
     *
     * @param pays the entity to save.
     * @return the persisted entity.
     */
    public Pays save(Pays pays) {
        log.debug("Request to save Pays : {}", pays);
        return paysRepository.save(pays);
    }

    /**
     * Get all the pays.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Pays> findAll(Pageable pageable) {
        log.debug("Request to get all Pays");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("pays"));
        //List<Pays> findAll = paysRepository.findAll(Sort.by("pays"));
        return paysRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Pays> findAll() {
        log.debug("Request to get all Pays");
        //List<Pays> findAll = paysRepository.findAll(Sort.by("pays"));
        return paysRepository.findAll(Sort.by("pays"));
    }

    /*@Transactional(readOnly = true)
    public List<Pays> findAllActifs() {
        log.debug("Request to get all Pays");
        //List<Pays> findAll = paysRepository.findAll(Sort.by("pays"));
        //List<Pays> paysList = paysRepository.findAll(Sort.by("pays"))
        return paysRepository.findAll(Sort.by("pays"));
    }*/

    /**
     * Get one pays by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Pays> findOne(Long id) {
        log.debug("Request to get Pays : {}", id);
        return paysRepository.findById(id);
    }

    /**
     * Delete the pays by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pays : {}", id);
        paysRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Pays findByIsoAlpha3(String isoAlpha3) {
        log.debug("Request to get Pays by isoAlpha3");
        return paysRepository.findByIsoAlpha3(isoAlpha3);
    }
}
