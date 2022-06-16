package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Evaluation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Evaluation}.
 */
public interface EvaluationService {
    /**
     * Save a evaluation.
     *
     * @param evaluation the entity to save.
     * @return the persisted entity.
     */
    Evaluation save(Evaluation evaluation);

    /**
     * Updates a evaluation.
     *
     * @param evaluation the entity to update.
     * @return the persisted entity.
     */
    Evaluation update(Evaluation evaluation);

    /**
     * Partially updates a evaluation.
     *
     * @param evaluation the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Evaluation> partialUpdate(Evaluation evaluation);

    /**
     * Get all the evaluations.
     *
     * @return the list of entities.
     */
    List<Evaluation> findAll();

    /**
     * Get the "id" evaluation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Evaluation> findOne(Long id);

    /**
     * Delete the "id" evaluation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
