package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Choix;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Choix}.
 */
public interface ChoixService {
    /**
     * Save a choix.
     *
     * @param choix the entity to save.
     * @return the persisted entity.
     */
    Choix save(Choix choix);

    /**
     * Updates a choix.
     *
     * @param choix the entity to update.
     * @return the persisted entity.
     */
    Choix update(Choix choix);

    /**
     * Partially updates a choix.
     *
     * @param choix the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Choix> partialUpdate(Choix choix);

    /**
     * Get all the choixes.
     *
     * @return the list of entities.
     */
    List<Choix> findAll();

    /**
     * Get the "id" choix.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Choix> findOne(Long id);

    /**
     * Delete the "id" choix.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
