package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Evenement;
import tn.esprit.pidev.repository.EvenementRepository;
import tn.esprit.pidev.services.api.EvenementService;
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
public class EvenementController {

    private final Logger log = LoggerFactory.getLogger(EvenementController.class);

    private static final String ENTITY_NAME = "evenement";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final EvenementService evenementService;

    private final EvenementRepository evenementRepository;

    public EvenementController(EvenementService evenementService, EvenementRepository evenementRepository) {
        this.evenementService = evenementService;
        this.evenementRepository = evenementRepository;
    }

    /**
     * {@code POST  /evenements} : Create a new evenement.
     *
     * @param evenement the evenement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new evenement, or with status {@code 400 (Bad Request)} if the evenement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/evenements")
    public ResponseEntity<Evenement> createEvenement(@RequestBody Evenement evenement) throws URISyntaxException {
        log.debug("REST request to save Evenement : {}", evenement);
        if (evenement.getId() != null) {
            throw new BadRequestAlertException("A new evenement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Evenement result = evenementService.save(evenement);
        return ResponseEntity
            .created(new URI("/api/evenements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /evenements/:id} : Updates an existing evenement.
     *
     * @param id the id of the evenement to save.
     * @param evenement the evenement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evenement,
     * or with status {@code 400 (Bad Request)} if the evenement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the evenement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/evenements/{id}")
    public ResponseEntity<Evenement> updateEvenement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Evenement evenement
    ) throws URISyntaxException {
        log.debug("REST request to update Evenement : {}, {}", id, evenement);
        if (evenement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evenement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evenementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Evenement result = evenementService.update(evenement);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, evenement.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /evenements/:id} : Partial updates given fields of an existing evenement, field will ignore if it is null
     *
     * @param id the id of the evenement to save.
     * @param evenement the evenement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated evenement,
     * or with status {@code 400 (Bad Request)} if the evenement is not valid,
     * or with status {@code 404 (Not Found)} if the evenement is not found,
     * or with status {@code 500 (Internal Server Error)} if the evenement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/evenements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Evenement> partialUpdateEvenement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Evenement evenement
    ) throws URISyntaxException {
        log.debug("REST request to partial update Evenement partially : {}, {}", id, evenement);
        if (evenement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, evenement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!evenementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Evenement> result = evenementService.partialUpdate(evenement);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, evenement.getId().toString())
        );
    }

    /**
     * {@code GET  /evenements} : get all the evenements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of evenements in body.
     */
    @GetMapping("/evenements")
    public List<Evenement> getAllEvenements() {
        log.debug("REST request to get all Evenements");
        return evenementService.findAll();
    }

    /**
     * {@code GET  /evenements/:id} : get the "id" evenement.
     *
     * @param id the id of the evenement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the evenement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/evenements/{id}")
    public ResponseEntity<Evenement> getEvenement(@PathVariable Long id) {
        log.debug("REST request to get Evenement : {}", id);
        Optional<Evenement> evenement = evenementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evenement);
    }

    /**
     * {@code DELETE  /evenements/:id} : delete the "id" evenement.
     *
     * @param id the id of the evenement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/evenements/{id}")
    public ResponseEntity<Void> deleteEvenement(@PathVariable Long id) {
        log.debug("REST request to delete Evenement : {}", id);
        evenementService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
