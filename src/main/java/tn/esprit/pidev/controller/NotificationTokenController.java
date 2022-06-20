package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.NotificationToken;
import tn.esprit.pidev.repository.NotificationTokenRepository;
import tn.esprit.pidev.services.api.NotificationTokenService;
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
public class NotificationTokenController {

    private final Logger log = LoggerFactory.getLogger(NotificationTokenController.class);

    private static final String ENTITY_NAME = "notificationToken";

    @Value("${esprit.clientApp.name}")
    private String applicationName;

    private final NotificationTokenService notificationTokenService;

    private final NotificationTokenRepository notificationTokenRepository;

    public NotificationTokenController(
        NotificationTokenService notificationTokenService,
        NotificationTokenRepository notificationTokenRepository
    ) {
        this.notificationTokenService = notificationTokenService;
        this.notificationTokenRepository = notificationTokenRepository;
    }

    /**
     * {@code POST  /notification-tokens} : Create a new notificationToken.
     *
     * @param notificationToken the notificationToken to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificationToken, or with status {@code 400 (Bad Request)} if the notificationToken has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notification-tokens")
    public ResponseEntity<NotificationToken> createNotificationToken(@RequestBody NotificationToken notificationToken)
        throws URISyntaxException {
        log.debug("REST request to save NotificationToken : {}", notificationToken);
        if (notificationToken.getId() != null) {
            throw new BadRequestAlertException("A new notificationToken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationToken result = notificationTokenService.save(notificationToken);
        return ResponseEntity
            .created(new URI("/api/notification-tokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notification-tokens/:id} : Updates an existing notificationToken.
     *
     * @param id the id of the notificationToken to save.
     * @param notificationToken the notificationToken to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationToken,
     * or with status {@code 400 (Bad Request)} if the notificationToken is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificationToken couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notification-tokens/{id}")
    public ResponseEntity<NotificationToken> updateNotificationToken(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotificationToken notificationToken
    ) throws URISyntaxException {
        log.debug("REST request to update NotificationToken : {}, {}", id, notificationToken);
        if (notificationToken.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notificationToken.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notificationTokenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NotificationToken result = notificationTokenService.update(notificationToken);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, notificationToken.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /notification-tokens/:id} : Partial updates given fields of an existing notificationToken, field will ignore if it is null
     *
     * @param id the id of the notificationToken to save.
     * @param notificationToken the notificationToken to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificationToken,
     * or with status {@code 400 (Bad Request)} if the notificationToken is not valid,
     * or with status {@code 404 (Not Found)} if the notificationToken is not found,
     * or with status {@code 500 (Internal Server Error)} if the notificationToken couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/notification-tokens/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NotificationToken> partialUpdateNotificationToken(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NotificationToken notificationToken
    ) throws URISyntaxException {
        log.debug("REST request to partial update NotificationToken partially : {}, {}", id, notificationToken);
        if (notificationToken.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, notificationToken.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!notificationTokenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NotificationToken> result = notificationTokenService.partialUpdate(notificationToken);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, notificationToken.getId().toString())
        );
    }

    /**
     * {@code GET  /notification-tokens} : get all the notificationTokens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificationTokens in body.
     */
    @GetMapping("/notification-tokens")
    public List<NotificationToken> getAllNotificationTokens() {
        log.debug("REST request to get all NotificationTokens");
        return notificationTokenService.findAll();
    }

    /**
     * {@code GET  /notification-tokens/:id} : get the "id" notificationToken.
     *
     * @param id the id of the notificationToken to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificationToken, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notification-tokens/{id}")
    public ResponseEntity<NotificationToken> getNotificationToken(@PathVariable Long id) {
        log.debug("REST request to get NotificationToken : {}", id);
        Optional<NotificationToken> notificationToken = notificationTokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationToken);
    }

    /**
     * {@code DELETE  /notification-tokens/:id} : delete the "id" notificationToken.
     *
     * @param id the id of the notificationToken to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notification-tokens/{id}")
    public ResponseEntity<Void> deleteNotificationToken(@PathVariable Long id) {
        log.debug("REST request to delete NotificationToken : {}", id);
        notificationTokenService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
