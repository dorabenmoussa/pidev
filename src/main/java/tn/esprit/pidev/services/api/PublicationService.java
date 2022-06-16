package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Publication;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Publication}.
 */
public interface PublicationService {
    /**
     * Save a publication.
     *
     * @param publication the entity to save.
     * @return the persisted entity.
     */
    Publication save(Publication publication);

    /**
     * Updates a publication.
     *
     * @param publication the entity to update.
     * @return the persisted entity.
     */
    Publication update(Publication publication);

    /**
     * Partially updates a publication.
     *
     * @param publication the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Publication> partialUpdate(Publication publication);

    /**
     * Get all the publications.
     *
     * @return the list of entities.
     */
    List<Publication> findAll();

    /**
     * Get the "id" publication.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Publication> findOne(Long id);

    /**
     * Delete the "id" publication.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
