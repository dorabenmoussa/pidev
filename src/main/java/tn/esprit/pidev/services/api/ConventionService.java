package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Convention;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Convention}.
 */
public interface ConventionService {
    /**
     * Save a convention.
     *
     * @param convention the entity to save.
     * @return the persisted entity.
     */
    Convention save(Convention convention);

    /**
     * Updates a convention.
     *
     * @param convention the entity to update.
     * @return the persisted entity.
     */
    Convention update(Convention convention);

    /**
     * Partially updates a convention.
     *
     * @param convention the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Convention> partialUpdate(Convention convention);

    /**
     * Get all the conventions.
     *
     * @return the list of entities.
     */
    List<Convention> findAll();

    /**
     * Get the "id" convention.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Convention> findOne(Long id);

    /**
     * Delete the "id" convention.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
