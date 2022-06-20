package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Offre;
import tn.esprit.pidev.repository.OffreRepository;
import tn.esprit.pidev.services.api.OffreService;
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
public class OffreController {

    private final Logger log = LoggerFactory.getLogger(OffreController.class);

    private static final String ENTITY_NAME = "offre";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final OffreService offreService;

    private final OffreRepository offreRepository;

    public OffreController(OffreService offreService, OffreRepository offreRepository) {
        this.offreService = offreService;
        this.offreRepository = offreRepository;
    }

    /**
     * {@code POST  /offres} : Create a new offre.
     *
     * @param offre the offre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offre, or with status {@code 400 (Bad Request)} if the offre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offres")
    public ResponseEntity<Offre> createOffre(@RequestBody Offre offre) throws URISyntaxException {
        log.debug("REST request to save Offre : {}", offre);
        if (offre.getId() != null) {
            throw new BadRequestAlertException("A new offre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Offre result = offreService.save(offre);
        return ResponseEntity
            .created(new URI("/api/offres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offres/:id} : Updates an existing offre.
     *
     * @param id the id of the offre to save.
     * @param offre the offre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offre,
     * or with status {@code 400 (Bad Request)} if the offre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offres/{id}")
    public ResponseEntity<Offre> updateOffre(@PathVariable(value = "id", required = false) final Long id, @RequestBody Offre offre)
        throws URISyntaxException {
        log.debug("REST request to update Offre : {}, {}", id, offre);
        if (offre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, offre.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!offreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Offre result = offreService.update(offre);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, offre.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /offres/:id} : Partial updates given fields of an existing offre, field will ignore if it is null
     *
     * @param id the id of the offre to save.
     * @param offre the offre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offre,
     * or with status {@code 400 (Bad Request)} if the offre is not valid,
     * or with status {@code 404 (Not Found)} if the offre is not found,
     * or with status {@code 500 (Internal Server Error)} if the offre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/offres/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Offre> partialUpdateOffre(@PathVariable(value = "id", required = false) final Long id, @RequestBody Offre offre)
        throws URISyntaxException {
        log.debug("REST request to partial update Offre partially : {}, {}", id, offre);
        if (offre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, offre.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!offreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Offre> result = offreService.partialUpdate(offre);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, offre.getId().toString())
        );
    }

    /**
     * {@code GET  /offres} : get all the offres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offres in body.
     */
    @GetMapping("/offres")
    public List<Offre> getAllOffres() {
        log.debug("REST request to get all Offres");
        return offreService.findAll();
    }

    /**
     * {@code GET  /offres/:id} : get the "id" offre.
     *
     * @param id the id of the offre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offres/{id}")
    public ResponseEntity<Offre> getOffre(@PathVariable Long id) {
        log.debug("REST request to get Offre : {}", id);
        Optional<Offre> offre = offreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(offre);
    }

    /**
     * {@code DELETE  /offres/:id} : delete the "id" offre.
     *
     * @param id the id of the offre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offres/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        log.debug("REST request to delete Offre : {}", id);
        offreService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
