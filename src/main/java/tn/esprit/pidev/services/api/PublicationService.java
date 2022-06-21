package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Publication;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Publication}.
 */
public interface PublicationService {
    /**
     * @param publication the entity to save.
     * @return the persisted entity.
     */
    Publication save(Publication publication);

    /**
     * @param publication the entity to update.
     * @return the persisted entity.
     */
    Publication update(Publication publication);

    /**
     * @param publication the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Publication> partialUpdate(Publication publication);

    /**
     * @return the list of entities.
     */
    List<Publication> findAll();

    /**
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Publication> findOne(Long id);

    /**
     * @param id the id of the entity.
     */
    void delete(Long id);
}
