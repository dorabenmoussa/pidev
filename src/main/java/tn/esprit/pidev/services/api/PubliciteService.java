package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Publicite;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Publicite}.
 */
public interface PubliciteService {
    /**
     * Save a publicite.
     *
     * @param publicite the entity to save.
     * @return the persisted entity.
     */
    Publicite save(Publicite publicite);

    /**
     * Updates a publicite.
     *
     * @param publicite the entity to update.
     * @return the persisted entity.
     */
    Publicite update(Publicite publicite);

    /**
     * Partially updates a publicite.
     *
     * @param publicite the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Publicite> partialUpdate(Publicite publicite);

    /**
     * Get all the publicites.
     *
     * @return the list of entities.
     */
    List<Publicite> findAll();

    /**
     * Get the "id" publicite.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Publicite> findOne(Long id);

    /**
     * Delete the "id" publicite.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
