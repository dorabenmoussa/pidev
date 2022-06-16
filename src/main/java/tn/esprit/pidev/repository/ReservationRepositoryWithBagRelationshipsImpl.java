package tn.esprit.pidev.repository;

import tn.esprit.pidev.entities.Reservation;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ReservationRepositoryWithBagRelationshipsImpl implements ReservationRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Reservation> fetchBagRelationships(Optional<Reservation> reservation) {
        return reservation.map(this::fetchEvenements);
    }

    @Override
    public Page<Reservation> fetchBagRelationships(Page<Reservation> reservations) {
        return new PageImpl<>(
            fetchBagRelationships(reservations.getContent()),
            reservations.getPageable(),
            reservations.getTotalElements()
        );
    }

    @Override
    public List<Reservation> fetchBagRelationships(List<Reservation> reservations) {
        return Optional.of(reservations).map(this::fetchEvenements).orElse(Collections.emptyList());
    }

    Reservation fetchEvenements(Reservation result) {
        return entityManager
            .createQuery(
                "select reservation from Reservation reservation left join fetch reservation.evenements where reservation is :reservation",
                Reservation.class
            )
            .setParameter("reservation", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Reservation> fetchEvenements(List<Reservation> reservations) {
        return entityManager
            .createQuery(
                "select distinct reservation from Reservation reservation left join fetch reservation.evenements where reservation in :reservations",
                Reservation.class
            )
            .setParameter("reservations", reservations)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
