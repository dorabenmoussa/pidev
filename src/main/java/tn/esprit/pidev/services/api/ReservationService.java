package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Reservation}.
 */
public interface ReservationService {
    /**
     * Save a reservation.
     *
     * @param reservation the entity to save.
     * @return the persisted entity.
     */
    Reservation save(Reservation reservation);

    /**
     * Updates a reservation.
     *
     * @param reservation the entity to update.
     * @return the persisted entity.
     */
    Reservation update(Reservation reservation);

    /**
     * Partially updates a reservation.
     *
     * @param reservation the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Reservation> partialUpdate(Reservation reservation);

    /**
     * Get all the reservations.
     *
     * @return the list of entities.
     */
    List<Reservation> findAll();

    /**
     * Get all the reservations with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Reservation> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" reservation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Reservation> findOne(String id);

    /**
     * Delete the "id" reservation.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
