package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Activite;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Activite}.
 */
public interface ActiviteService {
    /**
     * Save a activite.
     *
     * @param activite the entity to save.
     * @return the persisted entity.
     */
    Activite save(Activite activite);

    /**
     * Updates a activite.
     *
     * @param activite the entity to update.
     * @return the persisted entity.
     */
    Activite update(Activite activite);

    /**
     * Partially updates a activite.
     *
     * @param activite the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Activite> partialUpdate(Activite activite);

    /**
     * Get all the activites.
     *
     * @return the list of entities.
     */
    List<Activite> findAll();

    /**
     * Get the "id" activite.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Activite> findOne(Long id);

    /**
     * Delete the "id" activite.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
