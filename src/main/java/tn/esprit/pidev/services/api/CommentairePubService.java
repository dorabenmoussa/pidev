package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.CommentairePub;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link CommentairePub}.
 */
public interface CommentairePubService {
    /**
     * Save a commentairePub.
     *
     * @param commentairePub the entity to save.
     * @return the persisted entity.
     */
    CommentairePub save(CommentairePub commentairePub);

    /**
     * Updates a commentairePub.
     *
     * @param commentairePub the entity to update.
     * @return the persisted entity.
     */
    CommentairePub update(CommentairePub commentairePub);

    /**
     * Partially updates a commentairePub.
     *
     * @param commentairePub the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommentairePub> partialUpdate(CommentairePub commentairePub);

    /**
     * Get all the commentairePubs.
     *
     * @return the list of entities.
     */
    List<CommentairePub> findAll();

    /**
     * Get the "id" commentairePub.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommentairePub> findOne(Long id);

    /**
     * Delete the "id" commentairePub.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
