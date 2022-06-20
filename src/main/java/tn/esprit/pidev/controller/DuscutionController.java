package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Duscution;
import tn.esprit.pidev.repository.DuscutionRepository;
import tn.esprit.pidev.services.api.DuscutionService;
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
 * REST controller for managing {@link }.
 */
@RestController
@RequestMapping("/api")
public class DuscutionController {

    private final Logger log = LoggerFactory.getLogger(DuscutionController.class);

    private static final String ENTITY_NAME = "duscution";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final DuscutionService duscutionService;

    private final DuscutionRepository duscutionRepository;

    public DuscutionController(DuscutionService duscutionService, DuscutionRepository duscutionRepository) {
        this.duscutionService = duscutionService;
        this.duscutionRepository = duscutionRepository;
    }

    /**
     * {@code POST  /duscutions} : Create a new duscution.
     *
     * @param duscution the duscution to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new duscution, or with status {@code 400 (Bad Request)} if the duscution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/duscutions")
    public ResponseEntity<Duscution> createDuscution(@RequestBody Duscution duscution) throws URISyntaxException {
        log.debug("REST request to save Duscution : {}", duscution);
        if (duscution.getId() != null) {
            throw new BadRequestAlertException("A new duscution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Duscution result = duscutionService.save(duscution);
        return ResponseEntity
            .created(new URI("/api/duscutions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /duscutions/:id} : Updates an existing duscution.
     *
     * @param id the id of the duscution to save.
     * @param duscution the duscution to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duscution,
     * or with status {@code 400 (Bad Request)} if the duscution is not valid,
     * or with status {@code 500 (Internal Server Error)} if the duscution couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/duscutions/{id}")
    public ResponseEntity<Duscution> updateDuscution(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Duscution duscution
    ) throws URISyntaxException {
        log.debug("REST request to update Duscution : {}, {}", id, duscution);
        if (duscution.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, duscution.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duscutionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Duscution result = duscutionService.update(duscution);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, duscution.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /duscutions/:id} : Partial updates given fields of an existing duscution, field will ignore if it is null
     *
     * @param id the id of the duscution to save.
     * @param duscution the duscution to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated duscution,
     * or with status {@code 400 (Bad Request)} if the duscution is not valid,
     * or with status {@code 404 (Not Found)} if the duscution is not found,
     * or with status {@code 500 (Internal Server Error)} if the duscution couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/duscutions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Duscution> partialUpdateDuscution(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Duscution duscution
    ) throws URISyntaxException {
        log.debug("REST request to partial update Duscution partially : {}, {}", id, duscution);
        if (duscution.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, duscution.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!duscutionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Duscution> result = duscutionService.partialUpdate(duscution);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, duscution.getId().toString())
        );
    }

    /**
     * {@code GET  /duscutions} : get all the duscutions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of duscutions in body.
     */
    @GetMapping("/duscutions")
    public List<Duscution> getAllDuscutions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Duscutions");
        return duscutionService.findAll();
    }

    /**
     * {@code GET  /duscutions/:id} : get the "id" duscution.
     *
     * @param id the id of the duscution to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the duscution, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/duscutions/{id}")
    public ResponseEntity<Duscution> getDuscution(@PathVariable Long id) {
        log.debug("REST request to get Duscution : {}", id);
        Optional<Duscution> duscution = duscutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(duscution);
    }

    /**
     * {@code DELETE  /duscutions/:id} : delete the "id" duscution.
     *
     * @param id the id of the duscution to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/duscutions/{id}")
    public ResponseEntity<Void> deleteDuscution(@PathVariable Long id) {
        log.debug("REST request to delete Duscution : {}", id);
        duscutionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
