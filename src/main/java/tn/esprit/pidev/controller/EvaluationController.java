package tn.esprit.pidev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.pidev.entities.Evaluation;
import tn.esprit.pidev.repository.EvaluationRepository;
import tn.esprit.pidev.utils.HeaderUtil;
import tn.esprit.pidev.utils.ResponseUtil;
import tn.esprit.pidev.controller.errors.BadRequestAlertException;
import tn.esprit.pidev.services.api.EvaluationService;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 */
@RestController
@RequestMapping("/api")
public class EvaluationController {

    private final Logger log = LoggerFactory.getLogger(EvaluationController.class);

    private static final String ENTITY_NAME = "evaluation";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final EvaluationService evaluationService;

    private final EvaluationRepository evaluationRepository;

    public EvaluationController(EvaluationService evaluationService, EvaluationRepository evaluationRepository) {
        this.evaluationService = evaluationService;
        this.evaluationRepository = evaluationRepository;
    }

    /**
     * {@code POST  /evaluations} : Create a new evaluation.
     *
     * @param evaluation the evaluation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new evaluation, or with status {@code 400 (Bad Request)} if the evaluation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/evaluations")
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) throws URISyntaxException {
        log.debug("REST request to save Evaluation : {}", evaluation);
        if (evaluation.getId() != null) {
            throw new BadRequestAlertException("A new evaluation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Evaluation result = evaluationService.save(evaluation);
        return ResponseEntity
            .created(new URI("/api/evaluations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /evaluations/:id} : Updates an existing evaluation.
     *
     * @param id the id of the evaluation to save.
     * @param evaluation the evaluation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluation,
     * or with status {@code 400 (Bad Request)} if the evaluation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the evaluation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/evaluations/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Evaluation evaluation
    ) throws URISyntaxException {
        log.debug("REST request to update Evaluation : {}, {}", id, evaluation);
        if (evaluation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Evaluation result = evaluationService.update(evaluation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluation.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /evaluations/:id} : Partial updates given fields of an existing evaluation, field will ignore if it is null
     *
     * @param id the id of the evaluation to save.
     * @param evaluation the evaluation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evaluation,
     * or with status {@code 400 (Bad Request)} if the evaluation is not valid,
     * or with status {@code 404 (Not Found)} if the evaluation is not found,
     * or with status {@code 500 (Internal Server Error)} if the evaluation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/evaluations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Evaluation> partialUpdateEvaluation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Evaluation evaluation
    ) throws URISyntaxException {
        log.debug("REST request to partial update Evaluation partially : {}, {}", id, evaluation);
        if (evaluation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evaluation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evaluationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Evaluation> result = evaluationService.partialUpdate(evaluation);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, evaluation.getId().toString())
        );
    }

    /**
     * {@code GET  /evaluations} : get all the evaluations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evaluations in body.
     */
    @GetMapping("/evaluations")
    public List<Evaluation> getAllEvaluations() {
        log.debug("REST request to get all Evaluations");
        return evaluationService.findAll();
    }

    /**
     * {@code GET  /evaluations/:id} : get the "id" evaluation.
     *
     * @param id the id of the evaluation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evaluation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evaluations/{id}")
    public ResponseEntity<Evaluation> getEvaluation(@PathVariable Long id) {
        log.debug("REST request to get Evaluation : {}", id);
        Optional<Evaluation> evaluation = evaluationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluation);
    }

    /**
     * {@code DELETE  /evaluations/:id} : delete the "id" evaluation.
     *
     * @param id the id of the evaluation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/evaluations/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        log.debug("REST request to delete Evaluation : {}", id);
        evaluationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
