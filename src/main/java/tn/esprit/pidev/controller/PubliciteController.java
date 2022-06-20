package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Publicite;
import tn.esprit.pidev.repository.PubliciteRepository;
import tn.esprit.pidev.services.api.PubliciteService;
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
public class PubliciteController {

    private final Logger log = LoggerFactory.getLogger(PubliciteController.class);

    private static final String ENTITY_NAME = "publicite";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final PubliciteService publiciteService;

    private final PubliciteRepository publiciteRepository;

    public PubliciteController(PubliciteService publiciteService, PubliciteRepository publiciteRepository) {
        this.publiciteService = publiciteService;
        this.publiciteRepository = publiciteRepository;
    }

    /**
     * {@code POST  /publicites} : Create a new publicite.
     *
     * @param publicite the publicite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new publicite, or with status {@code 400 (Bad Request)} if the publicite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/publicites")
    public ResponseEntity<Publicite> createPublicite(@RequestBody Publicite publicite) throws URISyntaxException {
        log.debug("REST request to save Publicite : {}", publicite);
        if (publicite.getId() != null) {
            throw new BadRequestAlertException("A new publicite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Publicite result = publiciteService.save(publicite);
        return ResponseEntity
            .created(new URI("/api/publicites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /publicites/:id} : Updates an existing publicite.
     *
     * @param id the id of the publicite to save.
     * @param publicite the publicite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated publicite,
     * or with status {@code 400 (Bad Request)} if the publicite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the publicite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/publicites/{id}")
    public ResponseEntity<Publicite> updatePublicite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Publicite publicite
    ) throws URISyntaxException {
        log.debug("REST request to update Publicite : {}, {}", id, publicite);
        if (publicite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, publicite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!publiciteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Publicite result = publiciteService.update(publicite);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, publicite.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /publicites/:id} : Partial updates given fields of an existing publicite, field will ignore if it is null
     *
     * @param id the id of the publicite to save.
     * @param publicite the publicite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated publicite,
     * or with status {@code 400 (Bad Request)} if the publicite is not valid,
     * or with status {@code 404 (Not Found)} if the publicite is not found,
     * or with status {@code 500 (Internal Server Error)} if the publicite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/publicites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Publicite> partialUpdatePublicite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Publicite publicite
    ) throws URISyntaxException {
        log.debug("REST request to partial update Publicite partially : {}, {}", id, publicite);
        if (publicite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, publicite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!publiciteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Publicite> result = publiciteService.partialUpdate(publicite);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, publicite.getId().toString())
        );
    }

    /**
     * {@code GET  /publicites} : get all the publicites.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of publicites in body.
     */
    @GetMapping("/publicites")
    public List<Publicite> getAllPublicites() {
        log.debug("REST request to get all Publicites");
        return publiciteService.findAll();
    }

    /**
     * {@code GET  /publicites/:id} : get the "id" publicite.
     *
     * @param id the id of the publicite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the publicite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/publicites/{id}")
    public ResponseEntity<Publicite> getPublicite(@PathVariable Long id) {
        log.debug("REST request to get Publicite : {}", id);
        Optional<Publicite> publicite = publiciteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(publicite);
    }

    /**
     * {@code DELETE  /publicites/:id} : delete the "id" publicite.
     *
     * @param id the id of the publicite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/publicites/{id}")
    public ResponseEntity<Void> deletePublicite(@PathVariable Long id) {
        log.debug("REST request to delete Publicite : {}", id);
        publiciteService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
