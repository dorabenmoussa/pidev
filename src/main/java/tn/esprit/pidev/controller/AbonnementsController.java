package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Abonnements;
import tn.esprit.pidev.repository.AbonnementsRepository;
import tn.esprit.pidev.services.api.AbonnementsService;
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
public class AbonnementsController {

    private final Logger log = LoggerFactory.getLogger(AbonnementsController.class);

    private static final String ENTITY_NAME = "abonnements";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final AbonnementsService abonnementsService;

    private final AbonnementsRepository abonnementsRepository;

    public AbonnementsController(AbonnementsService abonnementsService, AbonnementsRepository abonnementsRepository) {
        this.abonnementsService = abonnementsService;
        this.abonnementsRepository = abonnementsRepository;
    }

    /**
     * {@code POST  /abonnements} : Create a new abonnements.
     *
     * @param abonnements the abonnements to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new abonnements, or with status {@code 400 (Bad Request)} if the abonnements has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/abonnements")
    public ResponseEntity<Abonnements> createAbonnements(@RequestBody Abonnements abonnements) throws URISyntaxException {
        log.debug("REST request to save Abonnements : {}", abonnements);
        if (abonnements.getId() != null) {
            throw new BadRequestAlertException("A new abonnements cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Abonnements result = abonnementsService.save(abonnements);
        return ResponseEntity
            .created(new URI("/api/abonnements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /abonnements/:id} : Updates an existing abonnements.
     *
     * @param id the id of the abonnements to save.
     * @param abonnements the abonnements to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated abonnements,
     * or with status {@code 400 (Bad Request)} if the abonnements is not valid,
     * or with status {@code 500 (Internal Server Error)} if the abonnements couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/abonnements/{id}")
    public ResponseEntity<Abonnements> updateAbonnements(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Abonnements abonnements
    ) throws URISyntaxException {
        log.debug("REST request to update Abonnements : {}, {}", id, abonnements);
        if (abonnements.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, abonnements.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!abonnementsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Abonnements result = abonnementsService.update(abonnements);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, abonnements.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /abonnements/:id} : Partial updates given fields of an existing abonnements, field will ignore if it is null
     *
     * @param id the id of the abonnements to save.
     * @param abonnements the abonnements to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated abonnements,
     * or with status {@code 400 (Bad Request)} if the abonnements is not valid,
     * or with status {@code 404 (Not Found)} if the abonnements is not found,
     * or with status {@code 500 (Internal Server Error)} if the abonnements couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/abonnements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Abonnements> partialUpdateAbonnements(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Abonnements abonnements
    ) throws URISyntaxException {
        log.debug("REST request to partial update Abonnements partially : {}, {}", id, abonnements);
        if (abonnements.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, abonnements.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!abonnementsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Abonnements> result = abonnementsService.partialUpdate(abonnements);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, abonnements.getId().toString())
        );
    }

    /**
     * {@code GET  /abonnements} : get all the abonnements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of abonnements in body.
     */
    @GetMapping("/abonnements")
    public List<Abonnements> getAllAbonnements() {
        log.debug("REST request to get all Abonnements");
        return abonnementsService.findAll();
    }

    /**
     * {@code GET  /abonnements/:id} : get the "id" abonnements.
     *
     * @param id the id of the abonnements to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the abonnements, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/abonnements/{id}")
    public ResponseEntity<Abonnements> getAbonnements(@PathVariable Long id) {
        log.debug("REST request to get Abonnements : {}", id);
        Optional<Abonnements> abonnements = abonnementsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(abonnements);
    }

    /**
     * {@code DELETE  /abonnements/:id} : delete the "id" abonnements.
     *
     * @param id the id of the abonnements to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/abonnements/{id}")
    public ResponseEntity<Void> deleteAbonnements(@PathVariable Long id) {
        log.debug("REST request to delete Abonnements : {}", id);
        abonnementsService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
