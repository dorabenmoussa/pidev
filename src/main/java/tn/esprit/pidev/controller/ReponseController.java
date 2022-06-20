package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Reponse;
import tn.esprit.pidev.repository.ReponseRepository;
import tn.esprit.pidev.services.api.ReponseService;
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
public class ReponseController {

    private final Logger log = LoggerFactory.getLogger(ReponseController.class);

    private static final String ENTITY_NAME = "reponse";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final ReponseService reponseService;

    private final ReponseRepository reponseRepository;

    public ReponseController(ReponseService reponseService, ReponseRepository reponseRepository) {
        this.reponseService = reponseService;
        this.reponseRepository = reponseRepository;
    }

    /**
     * {@code POST  /reponses} : Create a new reponse.
     *
     * @param reponse the reponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reponse, or with status {@code 400 (Bad Request)} if the reponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reponses")
    public ResponseEntity<Reponse> createReponse(@RequestBody Reponse reponse) throws URISyntaxException {
        log.debug("REST request to save Reponse : {}", reponse);
        if (reponse.getId() != null) {
            throw new BadRequestAlertException("A new reponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Reponse result = reponseService.save(reponse);
        return ResponseEntity
            .created(new URI("/api/reponses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reponses/:id} : Updates an existing reponse.
     *
     * @param id the id of the reponse to save.
     * @param reponse the reponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reponse,
     * or with status {@code 400 (Bad Request)} if the reponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reponses/{id}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable(value = "id", required = false) final Long id, @RequestBody Reponse reponse)
        throws URISyntaxException {
        log.debug("REST request to update Reponse : {}, {}", id, reponse);
        if (reponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Reponse result = reponseService.update(reponse);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reponse.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /reponses/:id} : Partial updates given fields of an existing reponse, field will ignore if it is null
     *
     * @param id the id of the reponse to save.
     * @param reponse the reponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reponse,
     * or with status {@code 400 (Bad Request)} if the reponse is not valid,
     * or with status {@code 404 (Not Found)} if the reponse is not found,
     * or with status {@code 500 (Internal Server Error)} if the reponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/reponses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Reponse> partialUpdateReponse(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Reponse reponse
    ) throws URISyntaxException {
        log.debug("REST request to partial update Reponse partially : {}, {}", id, reponse);
        if (reponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, reponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!reponseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Reponse> result = reponseService.partialUpdate(reponse);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reponse.getId().toString())
        );
    }

    /**
     * {@code GET  /reponses} : get all the reponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reponses in body.
     */
    @GetMapping("/reponses")
    public List<Reponse> getAllReponses() {
        log.debug("REST request to get all Reponses");
        return reponseService.findAll();
    }

    /**
     * {@code GET  /reponses/:id} : get the "id" reponse.
     *
     * @param id the id of the reponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reponses/{id}")
    public ResponseEntity<Reponse> getReponse(@PathVariable Long id) {
        log.debug("REST request to get Reponse : {}", id);
        Optional<Reponse> reponse = reponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reponse);
    }

    /**
     * {@code DELETE  /reponses/:id} : delete the "id" reponse.
     *
     * @param id the id of the reponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reponses/{id}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long id) {
        log.debug("REST request to delete Reponse : {}", id);
        reponseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
