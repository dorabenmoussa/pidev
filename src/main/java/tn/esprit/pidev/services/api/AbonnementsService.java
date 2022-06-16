package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Abonnements;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Abonnements}.
 */
public interface AbonnementsService {
    /**
     * Save a abonnements.
     *
     * @param abonnements the entity to save.
     * @return the persisted entity.
     */
    Abonnements save(Abonnements abonnements);

    /**
     * Updates a abonnements.
     *
     * @param abonnements the entity to update.
     * @return the persisted entity.
     */
    Abonnements update(Abonnements abonnements);

    /**
     * Partially updates a abonnements.
     *
     * @param abonnements the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Abonnements> partialUpdate(Abonnements abonnements);

    /**
     * Get all the abonnements.
     *
     * @return the list of entities.
     */
    List<Abonnements> findAll();

    /**
     * Get the "id" abonnements.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Abonnements> findOne(Long id);

    /**
     * Delete the "id" abonnements.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
