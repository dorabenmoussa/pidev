package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Badges;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Badges}.
 */
public interface BadgesService {
    /**
     * Save a badges.
     *
     * @param badges the entity to save.
     * @return the persisted entity.
     */
    Badges save(Badges badges);

    /**
     * Updates a badges.
     *
     * @param badges the entity to update.
     * @return the persisted entity.
     */
    Badges update(Badges badges);

    /**
     * Partially updates a badges.
     *
     * @param badges the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Badges> partialUpdate(Badges badges);

    /**
     * Get all the badges.
     *
     * @return the list of entities.
     */
    List<Badges> findAll();

    /**
     * Get the "id" badges.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Badges> findOne(Long id);

    /**
     * Delete the "id" badges.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
