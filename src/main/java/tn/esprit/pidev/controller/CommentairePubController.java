package tn.esprit.pidev.controller;

import tn.esprit.pidev.controller.errors.BadRequestAlertException;
import tn.esprit.pidev.entities.CommentairePub;
import tn.esprit.pidev.repository.CommentairePubRepository;
import tn.esprit.pidev.services.api.CommentairePubService;
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
public class CommentairePubController {

    private final Logger log = LoggerFactory.getLogger(CommentairePubController.class);

    private static final String ENTITY_NAME = "commentairePub";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final CommentairePubService commentairePubService;

    private final CommentairePubRepository commentairePubRepository;

    public CommentairePubController(CommentairePubService commentairePubService, CommentairePubRepository commentairePubRepository) {
        this.commentairePubService = commentairePubService;
        this.commentairePubRepository = commentairePubRepository;
    }

    /**
     * {@code POST  /commentaire-pubs} : Create a new commentairePub.
     *
     * @param commentairePub the commentairePub to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commentairePub, or with status {@code 400 (Bad Request)} if the commentairePub has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/commentaire-pubs")
    public ResponseEntity<CommentairePub> createCommentairePub(@RequestBody CommentairePub commentairePub) throws URISyntaxException {
        log.debug("REST request to save CommentairePub : {}", commentairePub);
        if (commentairePub.getId() != null) {
            throw new BadRequestAlertException("A new commentairePub cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommentairePub result = commentairePubService.save(commentairePub);
        return ResponseEntity
            .created(new URI("/api/commentaire-pubs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /commentaire-pubs/:id} : Updates an existing commentairePub.
     *
     * @param id the id of the commentairePub to save.
     * @param commentairePub the commentairePub to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commentairePub,
     * or with status {@code 400 (Bad Request)} if the commentairePub is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commentairePub couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/commentaire-pubs/{id}")
    public ResponseEntity<CommentairePub> updateCommentairePub(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommentairePub commentairePub
    ) throws URISyntaxException {
        log.debug("REST request to update CommentairePub : {}, {}", id, commentairePub);
        if (commentairePub.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commentairePub.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commentairePubRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CommentairePub result = commentairePubService.update(commentairePub);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commentairePub.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /commentaire-pubs/:id} : Partial updates given fields of an existing commentairePub, field will ignore if it is null
     *
     * @param id the id of the commentairePub to save.
     * @param commentairePub the commentairePub to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commentairePub,
     * or with status {@code 400 (Bad Request)} if the commentairePub is not valid,
     * or with status {@code 404 (Not Found)} if the commentairePub is not found,
     * or with status {@code 500 (Internal Server Error)} if the commentairePub couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/commentaire-pubs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommentairePub> partialUpdateCommentairePub(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommentairePub commentairePub
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommentairePub partially : {}, {}", id, commentairePub);
        if (commentairePub.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commentairePub.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commentairePubRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommentairePub> result = commentairePubService.partialUpdate(commentairePub);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commentairePub.getId().toString())
        );
    }

    /**
     * {@code GET  /commentaire-pubs} : get all the commentairePubs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commentairePubs in body.
     */
    @GetMapping("/commentaire-pubs")
    public List<CommentairePub> getAllCommentairePubs() {
        log.debug("REST request to get all CommentairePubs");
        return commentairePubService.findAll();
    }

    /**
     * {@code GET  /commentaire-pubs/:id} : get the "id" commentairePub.
     *
     * @param id the id of the commentairePub to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commentairePub, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/commentaire-pubs/{id}")
    public ResponseEntity<CommentairePub> getCommentairePub(@PathVariable Long id) {
        log.debug("REST request to get CommentairePub : {}", id);
        Optional<CommentairePub> commentairePub = commentairePubService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commentairePub);
    }

    /**
     * {@code DELETE  /commentaire-pubs/:id} : delete the "id" commentairePub.
     *
     * @param id the id of the commentairePub to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/commentaire-pubs/{id}")
    public ResponseEntity<Void> deleteCommentairePub(@PathVariable Long id) {
        log.debug("REST request to delete CommentairePub : {}", id);
        commentairePubService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
