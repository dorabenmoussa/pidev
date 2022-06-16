package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Departement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Departement}.
 */
public interface DepartementService {
    /**
     * Save a departement.
     *
     * @param departement the entity to save.
     * @return the persisted entity.
     */
    Departement save(Departement departement);

    /**
     * Updates a departement.
     *
     * @param departement the entity to update.
     * @return the persisted entity.
     */
    Departement update(Departement departement);

    /**
     * Partially updates a departement.
     *
     * @param departement the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Departement> partialUpdate(Departement departement);

    /**
     * Get all the departements.
     *
     * @return the list of entities.
     */
    List<Departement> findAll();

    /**
     * Get the "id" departement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Departement> findOne(Long id);

    /**
     * Delete the "id" departement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
