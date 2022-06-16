package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Partenaire;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Partenaire}.
 */
public interface PartenaireService {
    /**
     * Save a partenaire.
     *
     * @param partenaire the entity to save.
     * @return the persisted entity.
     */
    Partenaire save(Partenaire partenaire);

    /**
     * Updates a partenaire.
     *
     * @param partenaire the entity to update.
     * @return the persisted entity.
     */
    Partenaire update(Partenaire partenaire);

    /**
     * Partially updates a partenaire.
     *
     * @param partenaire the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Partenaire> partialUpdate(Partenaire partenaire);

    /**
     * Get all the partenaires.
     *
     * @return the list of entities.
     */
    List<Partenaire> findAll();

    /**
     * Get the "id" partenaire.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Partenaire> findOne(Long id);

    /**
     * Delete the "id" partenaire.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
