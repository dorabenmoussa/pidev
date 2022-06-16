package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.NotificationToken;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link NotificationToken}.
 */
public interface NotificationTokenService {
    /**
     * Save a notificationToken.
     *
     * @param notificationToken the entity to save.
     * @return the persisted entity.
     */
    NotificationToken save(NotificationToken notificationToken);

    /**
     * Updates a notificationToken.
     *
     * @param notificationToken the entity to update.
     * @return the persisted entity.
     */
    NotificationToken update(NotificationToken notificationToken);

    /**
     * Partially updates a notificationToken.
     *
     * @param notificationToken the entity to update partially.
     * @return the persisted entity.
     */
    Optional<NotificationToken> partialUpdate(NotificationToken notificationToken);

    /**
     * Get all the notificationTokens.
     *
     * @return the list of entities.
     */
    List<NotificationToken> findAll();

    /**
     * Get the "id" notificationToken.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificationToken> findOne(Long id);

    /**
     * Delete the "id" notificationToken.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
