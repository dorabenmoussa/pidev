package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Entreprise;
import tn.esprit.pidev.repository.EntrepriseRepository;
import tn.esprit.pidev.services.api.EntrepriseService;
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
public class EntrepriseController {

    private final Logger log = LoggerFactory.getLogger(EntrepriseController.class);

    private static final String ENTITY_NAME = "entreprise";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final EntrepriseService entrepriseService;

    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseController(EntrepriseService entrepriseService, EntrepriseRepository entrepriseRepository) {
        this.entrepriseService = entrepriseService;
        this.entrepriseRepository = entrepriseRepository;
    }

    /**
     * {@code POST  /entreprises} : Create a new entreprise.
     *
     * @param entreprise the entreprise to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new entreprise, or with status {@code 400 (Bad Request)} if the entreprise has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/entreprises")
    public ResponseEntity<Entreprise> createEntreprise(@RequestBody Entreprise entreprise) throws URISyntaxException {
        log.debug("REST request to save Entreprise : {}", entreprise);
        if (entreprise.getId() != null) {
            throw new BadRequestAlertException("A new entreprise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Entreprise result = entrepriseService.save(entreprise);
        return ResponseEntity
            .created(new URI("/api/entreprises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /entreprises/:id} : Updates an existing entreprise.
     *
     * @param id the id of the entreprise to save.
     * @param entreprise the entreprise to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entreprise,
     * or with status {@code 400 (Bad Request)} if the entreprise is not valid,
     * or with status {@code 500 (Internal Server Error)} if the entreprise couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/entreprises/{id}")
    public ResponseEntity<Entreprise> updateEntreprise(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Entreprise entreprise
    ) throws URISyntaxException {
        log.debug("REST request to update Entreprise : {}, {}", id, entreprise);
        if (entreprise.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entreprise.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entrepriseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Entreprise result = entrepriseService.update(entreprise);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entreprise.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /entreprises/:id} : Partial updates given fields of an existing entreprise, field will ignore if it is null
     *
     * @param id the id of the entreprise to save.
     * @param entreprise the entreprise to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated entreprise,
     * or with status {@code 400 (Bad Request)} if the entreprise is not valid,
     * or with status {@code 404 (Not Found)} if the entreprise is not found,
     * or with status {@code 500 (Internal Server Error)} if the entreprise couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/entreprises/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Entreprise> partialUpdateEntreprise(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Entreprise entreprise
    ) throws URISyntaxException {
        log.debug("REST request to partial update Entreprise partially : {}, {}", id, entreprise);
        if (entreprise.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, entreprise.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!entrepriseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Entreprise> result = entrepriseService.partialUpdate(entreprise);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, entreprise.getId().toString())
        );
    }

    /**
     * {@code GET  /entreprises} : get all the entreprises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of entreprises in body.
     */
    @GetMapping("/entreprises")
    public List<Entreprise> getAllEntreprises() {
        log.debug("REST request to get all Entreprises");
        return entrepriseService.findAll();
    }

    /**
     * {@code GET  /entreprises/:id} : get the "id" entreprise.
     *
     * @param id the id of the entreprise to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the entreprise, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/entreprises/{id}")
    public ResponseEntity<Entreprise> getEntreprise(@PathVariable Long id) {
        log.debug("REST request to get Entreprise : {}", id);
        Optional<Entreprise> entreprise = entrepriseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(entreprise);
    }

    /**
     * {@code DELETE  /entreprises/:id} : delete the "id" entreprise.
     *
     * @param id the id of the entreprise to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/entreprises/{id}")
    public ResponseEntity<Void> deleteEntreprise(@PathVariable Long id) {
        log.debug("REST request to delete Entreprise : {}", id);
        entrepriseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
