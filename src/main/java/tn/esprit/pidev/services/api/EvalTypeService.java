package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.EvalType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link EvalType}.
 */
public interface EvalTypeService {
    /**
     * Save a evalType.
     *
     * @param evalType the entity to save.
     * @return the persisted entity.
     */
    EvalType save(EvalType evalType);

    /**
     * Updates a evalType.
     *
     * @param evalType the entity to update.
     * @return the persisted entity.
     */
    EvalType update(EvalType evalType);

    /**
     * Partially updates a evalType.
     *
     * @param evalType the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EvalType> partialUpdate(EvalType evalType);

    /**
     * Get all the evalTypes.
     *
     * @return the list of entities.
     */
    List<EvalType> findAll();

    /**
     * Get the "id" evalType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EvalType> findOne(Long id);

    /**
     * Delete the "id" evalType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
