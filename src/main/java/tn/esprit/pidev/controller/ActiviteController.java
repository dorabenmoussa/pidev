package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Activite;
import tn.esprit.pidev.repository.ActiviteRepository;
import tn.esprit.pidev.services.api.ActiviteService;
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

@RestController
@RequestMapping("/api")
public class ActiviteController {

    private final Logger log = LoggerFactory.getLogger(ActiviteController.class);

    private static final String ENTITY_NAME = "activite";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final ActiviteService activiteService;

    private final ActiviteRepository activiteRepository;

    public ActiviteController(ActiviteService activiteService, ActiviteRepository activiteRepository) {
        this.activiteService = activiteService;
        this.activiteRepository = activiteRepository;
    }

    /**
     * {@code POST  /activites} : Create a new activite.
     *
     * @param activite the activite to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new activite, or with status {@code 400 (Bad Request)} if the activite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/activites")
    public ResponseEntity<Activite> createActivite(@RequestBody Activite activite) throws URISyntaxException {
        log.debug("REST request to save Activite : {}", activite);
        if (activite.getId() != null) {
            throw new BadRequestAlertException("A new activite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Activite result = activiteService.save(activite);
        return ResponseEntity
            .created(new URI("/api/activites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /activites/:id} : Updates an existing activite.
     *
     * @param id the id of the activite to save.
     * @param activite the activite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activite,
     * or with status {@code 400 (Bad Request)} if the activite is not valid,
     * or with status {@code 500 (Internal Server Error)} if the activite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/activites/{id}")
    public ResponseEntity<Activite> updateActivite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Activite activite
    ) throws URISyntaxException {
        log.debug("REST request to update Activite : {}, {}", id, activite);
        if (activite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, activite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!activiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Activite result = activiteService.update(activite);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, activite.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /activites/:id} : Partial updates given fields of an existing activite, field will ignore if it is null
     *
     * @param id the id of the activite to save.
     * @param activite the activite to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated activite,
     * or with status {@code 400 (Bad Request)} if the activite is not valid,
     * or with status {@code 404 (Not Found)} if the activite is not found,
     * or with status {@code 500 (Internal Server Error)} if the activite couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/activites/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Activite> partialUpdateActivite(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Activite activite
    ) throws URISyntaxException {
        log.debug("REST request to partial update Activite partially : {}, {}", id, activite);
        if (activite.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, activite.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!activiteRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Activite> result = activiteService.partialUpdate(activite);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, activite.getId().toString())
        );
    }

    /**
     * {@code GET  /activites} : get all the activites.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of activites in body.
     */
    @GetMapping("/activites")
    public List<Activite> getAllActivites() {
        log.debug("REST request to get all Activites");
        return activiteService.findAll();
    }

    /**
     * {@code GET  /activites/:id} : get the "id" activite.
     *
     * @param id the id of the activite to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activite, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/activites/{id}")
    public ResponseEntity<Activite> getActivite(@PathVariable Long id) {
        log.debug("REST request to get Activite : {}", id);
        Optional<Activite> activite = activiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activite);
    }

    /**
     * {@code DELETE  /activites/:id} : delete the "id" activite.
     *
     * @param id the id of the activite to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/activites/{id}")
    public ResponseEntity<Void> deleteActivite(@PathVariable Long id) {
        log.debug("REST request to delete Activite : {}", id);
        activiteService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
