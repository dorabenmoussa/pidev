package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Duscution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Duscution}.
 */
public interface DuscutionService {
    /**
     * Save a duscution.
     *
     * @param duscution the entity to save.
     * @return the persisted entity.
     */
    Duscution save(Duscution duscution);

    /**
     * Updates a duscution.
     *
     * @param duscution the entity to update.
     * @return the persisted entity.
     */
    Duscution update(Duscution duscution);

    /**
     * Partially updates a duscution.
     *
     * @param duscution the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Duscution> partialUpdate(Duscution duscution);

    /**
     * Get all the duscutions.
     *
     * @return the list of entities.
     */
    List<Duscution> findAll();

    /**
     * Get all the duscutions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Duscution> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" duscution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Duscution> findOne(Long id);

    /**
     * Delete the "id" duscution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
