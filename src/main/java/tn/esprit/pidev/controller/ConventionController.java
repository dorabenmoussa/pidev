package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Convention;
import tn.esprit.pidev.repository.ConventionRepository;
import tn.esprit.pidev.services.api.ConventionService;
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
 * REST controller for managing {}.
 */
@RestController
@RequestMapping("/api")
public class ConventionController {

    private final Logger log = LoggerFactory.getLogger(ConventionController.class);

    private static final String ENTITY_NAME = "convention";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final ConventionService conventionService;

    private final ConventionRepository conventionRepository;

    public ConventionController(ConventionService conventionService, ConventionRepository conventionRepository) {
        this.conventionService = conventionService;
        this.conventionRepository = conventionRepository;
    }

    /**
     * {@code POST  /conventions} : Create a new convention.
     *
     * @param convention the convention to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new convention, or with status {@code 400 (Bad Request)} if the convention has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conventions")
    public ResponseEntity<Convention> createConvention(@RequestBody Convention convention) throws URISyntaxException {
        log.debug("REST request to save Convention : {}", convention);
        if (convention.getId() != null) {
            throw new BadRequestAlertException("A new convention cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Convention result = conventionService.save(convention);
        return ResponseEntity
            .created(new URI("/api/conventions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conventions/:id} : Updates an existing convention.
     *
     * @param id the id of the convention to save.
     * @param convention the convention to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated convention,
     * or with status {@code 400 (Bad Request)} if the convention is not valid,
     * or with status {@code 500 (Internal Server Error)} if the convention couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conventions/{id}")
    public ResponseEntity<Convention> updateConvention(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Convention convention
    ) throws URISyntaxException {
        log.debug("REST request to update Convention : {}, {}", id, convention);
        if (convention.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, convention.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!conventionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Convention result = conventionService.update(convention);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, convention.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /conventions/:id} : Partial updates given fields of an existing convention, field will ignore if it is null
     *
     * @param id the id of the convention to save.
     * @param convention the convention to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated convention,
     * or with status {@code 400 (Bad Request)} if the convention is not valid,
     * or with status {@code 404 (Not Found)} if the convention is not found,
     * or with status {@code 500 (Internal Server Error)} if the convention couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/conventions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Convention> partialUpdateConvention(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Convention convention
    ) throws URISyntaxException {
        log.debug("REST request to partial update Convention partially : {}, {}", id, convention);
        if (convention.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, convention.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!conventionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Convention> result = conventionService.partialUpdate(convention);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, convention.getId().toString())
        );
    }

    /**
     * {@code GET  /conventions} : get all the conventions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conventions in body.
     */
    @GetMapping("/conventions")
    public List<Convention> getAllConventions() {
        log.debug("REST request to get all Conventions");
        return conventionService.findAll();
    }

    /**
     * {@code GET  /conventions/:id} : get the "id" convention.
     *
     * @param id the id of the convention to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the convention, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conventions/{id}")
    public ResponseEntity<Convention> getConvention(@PathVariable Long id) {
        log.debug("REST request to get Convention : {}", id);
        Optional<Convention> convention = conventionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(convention);
    }

    /**
     * {@code DELETE  /conventions/:id} : delete the "id" convention.
     *
     * @param id the id of the convention to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conventions/{id}")
    public ResponseEntity<Void> deleteConvention(@PathVariable Long id) {
        log.debug("REST request to delete Convention : {}", id);
        conventionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
