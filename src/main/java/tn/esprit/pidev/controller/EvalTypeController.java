package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.EvalType;
import tn.esprit.pidev.repository.EvalTypeRepository;
import tn.esprit.pidev.services.api.EvalTypeService;
import tn.esprit.pidev.controller.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.utils.HeaderUtil;
import tn.esprit.pidev.utils.ResponseUtil;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing .
 */
@RestController
@RequestMapping("/api")
public class EvalTypeController {

    private final Logger log = LoggerFactory.getLogger(EvalTypeController.class);

    private static final String ENTITY_NAME = "evalType";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final EvalTypeService evalTypeService;

    private final EvalTypeRepository evalTypeRepository;

    public EvalTypeController(EvalTypeService evalTypeService, EvalTypeRepository evalTypeRepository) {
        this.evalTypeService = evalTypeService;
        this.evalTypeRepository = evalTypeRepository;
    }

    /**
     * {@code POST  /eval-types} : Create a new evalType.
     *
     * @param evalType the evalType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new evalType, or with status {@code 400 (Bad Request)} if the evalType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/eval-types")
    public ResponseEntity<EvalType> createEvalType(@RequestBody EvalType evalType) throws URISyntaxException {
        log.debug("REST request to save EvalType : {}", evalType);
        if (evalType.getId() != null) {
            throw new BadRequestAlertException("A new evalType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvalType result = evalTypeService.save(evalType);
        return ResponseEntity
            .created(new URI("/api/eval-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /eval-types/:id} : Updates an existing evalType.
     *
     * @param id the id of the evalType to save.
     * @param evalType the evalType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evalType,
     * or with status {@code 400 (Bad Request)} if the evalType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the evalType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/eval-types/{id}")
    public ResponseEntity<EvalType> updateEvalType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvalType evalType
    ) throws URISyntaxException {
        log.debug("REST request to update EvalType : {}, {}", id, evalType);
        if (evalType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evalType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evalTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EvalType result = evalTypeService.update(evalType);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, evalType.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /eval-types/:id} : Partial updates given fields of an existing evalType, field will ignore if it is null
     *
     * @param id the id of the evalType to save.
     * @param evalType the evalType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evalType,
     * or with status {@code 400 (Bad Request)} if the evalType is not valid,
     * or with status {@code 404 (Not Found)} if the evalType is not found,
     * or with status {@code 500 (Internal Server Error)} if the evalType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/eval-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EvalType> partialUpdateEvalType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EvalType evalType
    ) throws URISyntaxException {
        log.debug("REST request to partial update EvalType partially : {}, {}", id, evalType);
        if (evalType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evalType.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evalTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EvalType> result = evalTypeService.partialUpdate(evalType);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, evalType.getId().toString())
        );
    }

    /**
     * {@code GET  /eval-types} : get all the evalTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evalTypes in body.
     */
    @GetMapping("/eval-types")
    public List<EvalType> getAllEvalTypes() {
        log.debug("REST request to get all EvalTypes");
        return evalTypeService.findAll();
    }

    /**
     * {@code GET  /eval-types/:id} : get the "id" evalType.
     *
     * @param id the id of the evalType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evalType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/eval-types/{id}")
    public ResponseEntity<EvalType> getEvalType(@PathVariable Long id) {
        log.debug("REST request to get EvalType : {}", id);
        Optional<EvalType> evalType = evalTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evalType);
    }

    /**
     * {@code DELETE  /eval-types/:id} : delete the "id" evalType.
     *
     * @param id the id of the evalType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/eval-types/{id}")
    public ResponseEntity<Void> deleteEvalType(@PathVariable Long id) {
        log.debug("REST request to delete EvalType : {}", id);
        evalTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
