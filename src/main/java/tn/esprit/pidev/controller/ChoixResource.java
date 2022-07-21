package tn.esprit.pidev.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.esprit.pidev.entities.Choix;
import tn.esprit.pidev.repository.ChoixRepository;
import tn.esprit.pidev.services.api.ChoixService;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import tn.esprit.pidev.utils.HeaderUtil;
import tn.esprit.pidev.utils.ResponseUtil;
import tn.esprit.pidev.controller.errors.BadRequestAlertException;

/**

 */
@RestController
@RequestMapping("/api")
public class ChoixResource {

    private final Logger log = LoggerFactory.getLogger(ChoixResource.class);

    private static final String ENTITY_NAME = "choix";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final ChoixService choixService;

    private final ChoixRepository choixRepository;

    public ChoixResource(ChoixService choixService, ChoixRepository choixRepository) {
        this.choixService = choixService;
        this.choixRepository = choixRepository;
    }

    /**
     * {@code POST  /choixes} : Create a new choix.
     *
     * @param choix the choix to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new choix, or with status {@code 400 (Bad Request)} if the choix has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/choixes")
    public ResponseEntity<Choix> createChoix(@RequestBody Choix choix) throws URISyntaxException {
        log.debug("REST request to save Choix : {}", choix);
        if (choix.getId() != null) {
            throw new BadRequestAlertException("A new choix cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Choix result = choixService.save(choix);
        return ResponseEntity
            .created(new URI("/api/choixes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /choixes/:id} : Updates an existing choix.
     *
     * @param id the id of the choix to save.
     * @param choix the choix to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated choix,
     * or with status {@code 400 (Bad Request)} if the choix is not valid,
     * or with status {@code 500 (Internal Server Error)} if the choix couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/choixes/{id}")
    public ResponseEntity<Choix> updateChoix(@PathVariable(value = "id", required = false) final Long id, @RequestBody Choix choix)
        throws URISyntaxException {
        log.debug("REST request to update Choix : {}, {}", id, choix);
        if (choix.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, choix.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!choixRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Choix result = choixService.update(choix);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, choix.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /choixes/:id} : Partial updates given fields of an existing choix, field will ignore if it is null
     *
     * @param id the id of the choix to save.
     * @param choix the choix to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated choix,
     * or with status {@code 400 (Bad Request)} if the choix is not valid,
     * or with status {@code 404 (Not Found)} if the choix is not found,
     * or with status {@code 500 (Internal Server Error)} if the choix couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/choixes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Choix> partialUpdateChoix(@PathVariable(value = "id", required = false) final Long id, @RequestBody Choix choix)
        throws URISyntaxException {
        log.debug("REST request to partial update Choix partially : {}, {}", id, choix);
        if (choix.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, choix.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!choixRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Choix> result = choixService.partialUpdate(choix);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, choix.getId().toString())
        );
    }

    /**
     * {@code GET  /choixes} : get all the choixes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of choixes in body.
     */
    @GetMapping("/choixes")
    public List<Choix> getAllChoixes() {
        log.debug("REST request to get all Choixes");
        return choixService.findAll();
    }

    /**
     * {@code GET  /choixes/:id} : get the "id" choix.
     *
     * @param id the id of the choix to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the choix, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/choixes/{id}")
    public ResponseEntity<Choix> getChoix(@PathVariable Long id) {
        log.debug("REST request to get Choix : {}", id);
        Optional<Choix> choix = choixService.findOne(id);
        return ResponseUtil.wrapOrNotFound(choix);
    }

    /**
     * {@code DELETE  /choixes/:id} : delete the "id" choix.
     *
     * @param id the id of the choix to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/choixes/{id}")
    public ResponseEntity<Void> deleteChoix(@PathVariable Long id) {
        log.debug("REST request to delete Choix : {}", id);
        choixService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
