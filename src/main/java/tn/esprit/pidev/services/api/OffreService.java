package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Offre;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Offre}.
 */
public interface OffreService {
    /**
     * Save a offre.
     *
     * @param offre the entity to save.
     * @return the persisted entity.
     */
    Offre save(Offre offre);

    /**
     * Updates a offre.
     *
     * @param offre the entity to update.
     * @return the persisted entity.
     */
    Offre update(Offre offre);

    /**
     * Partially updates a offre.
     *
     * @param offre the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Offre> partialUpdate(Offre offre);

    /**
     * Get all the offres.
     *
     * @return the list of entities.
     */
    List<Offre> findAll();

    /**
     * Get the "id" offre.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Offre> findOne(Long id);

    /**
     * Delete the "id" offre.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
