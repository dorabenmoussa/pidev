package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Partenaire;
import tn.esprit.pidev.repository.PartenaireRepository;
import tn.esprit.pidev.services.api.PartenaireService;
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
public class PartenaireController {

    private final Logger log = LoggerFactory.getLogger(PartenaireController.class);

    private static final String ENTITY_NAME = "partenaire";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final PartenaireService partenaireService;

    private final PartenaireRepository partenaireRepository;

    public PartenaireController(PartenaireService partenaireService, PartenaireRepository partenaireRepository) {
        this.partenaireService = partenaireService;
        this.partenaireRepository = partenaireRepository;
    }

    /**
     * {@code POST  /partenaires} : Create a new partenaire.
     *
     * @param partenaire the partenaire to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new partenaire, or with status {@code 400 (Bad Request)} if the partenaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/partenaires")
    public ResponseEntity<Partenaire> createPartenaire(@RequestBody Partenaire partenaire) throws URISyntaxException {
        log.debug("REST request to save Partenaire : {}", partenaire);
        if (partenaire.getId() != null) {
            throw new BadRequestAlertException("A new partenaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Partenaire result = partenaireService.save(partenaire);
        return ResponseEntity
            .created(new URI("/api/partenaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /partenaires/:id} : Updates an existing partenaire.
     *
     * @param id the id of the partenaire to save.
     * @param partenaire the partenaire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partenaire,
     * or with status {@code 400 (Bad Request)} if the partenaire is not valid,
     * or with status {@code 500 (Internal Server Error)} if the partenaire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/partenaires/{id}")
    public ResponseEntity<Partenaire> updatePartenaire(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Partenaire partenaire
    ) throws URISyntaxException {
        log.debug("REST request to update Partenaire : {}, {}", id, partenaire);
        if (partenaire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partenaire.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partenaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Partenaire result = partenaireService.update(partenaire);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partenaire.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /partenaires/:id} : Partial updates given fields of an existing partenaire, field will ignore if it is null
     *
     * @param id the id of the partenaire to save.
     * @param partenaire the partenaire to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partenaire,
     * or with status {@code 400 (Bad Request)} if the partenaire is not valid,
     * or with status {@code 404 (Not Found)} if the partenaire is not found,
     * or with status {@code 500 (Internal Server Error)} if the partenaire couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/partenaires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Partenaire> partialUpdatePartenaire(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Partenaire partenaire
    ) throws URISyntaxException {
        log.debug("REST request to partial update Partenaire partially : {}, {}", id, partenaire);
        if (partenaire.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partenaire.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partenaireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Partenaire> result = partenaireService.partialUpdate(partenaire);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partenaire.getId().toString())
        );
    }

    /**
     * {@code GET  /partenaires} : get all the partenaires.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of partenaires in body.
     */
    @GetMapping("/partenaires")
    public List<Partenaire> getAllPartenaires() {
        log.debug("REST request to get all Partenaires");
        return partenaireService.findAll();
    }

    /**
     * {@code GET  /partenaires/:id} : get the "id" partenaire.
     *
     * @param id the id of the partenaire to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the partenaire, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/partenaires/{id}")
    public ResponseEntity<Partenaire> getPartenaire(@PathVariable Long id) {
        log.debug("REST request to get Partenaire : {}", id);
        Optional<Partenaire> partenaire = partenaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(partenaire);
    }

    /**
     * {@code DELETE  /partenaires/:id} : delete the "id" partenaire.
     *
     * @param id the id of the partenaire to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/partenaires/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable Long id) {
        log.debug("REST request to delete Partenaire : {}", id);
        partenaireService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
