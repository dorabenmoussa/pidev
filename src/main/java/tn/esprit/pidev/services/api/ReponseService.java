package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Reponse;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Reponse}.
 */
public interface ReponseService {
    /**
     * Save a reponse.
     *
     * @param reponse the entity to save.
     * @return the persisted entity.
     */
    Reponse save(Reponse reponse);

    /**
     * Updates a reponse.
     *
     * @param reponse the entity to update.
     * @return the persisted entity.
     */
    Reponse update(Reponse reponse);

    /**
     * Partially updates a reponse.
     *
     * @param reponse the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Reponse> partialUpdate(Reponse reponse);

    /**
     * Get all the reponses.
     *
     * @return the list of entities.
     */
    List<Reponse> findAll();

    /**
     * Get the "id" reponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Reponse> findOne(Long id);

    /**
     * Delete the "id" reponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
