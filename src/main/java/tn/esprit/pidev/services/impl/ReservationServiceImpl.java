package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Reservation;
import tn.esprit.pidev.repository.ReservationRepository;
import tn.esprit.pidev.services.api.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Reservation}.
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        log.debug("Request to save Reservation : {}", reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation update(Reservation reservation) {
        log.debug("Request to save Reservation : {}", reservation);
        reservation.setIsPersisted();
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> partialUpdate(Reservation reservation) {
        log.debug("Request to partially update Reservation : {}", reservation);

        return reservationRepository
            .findById(reservation.getId())
            .map(existingReservation -> {
                if (reservation.getReservationDate() != null) {
                    existingReservation.setReservationDate(reservation.getReservationDate());
                }
                if (reservation.getReservationType() != null) {
                    existingReservation.setReservationType(reservation.getReservationType());
                }

                return existingReservation;
            })
            .map(reservationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        log.debug("Request to get all Reservations");
        return reservationRepository.findAllWithEagerRelationships();
    }

    public Page<Reservation> findAllWithEagerRelationships(Pageable pageable) {
        return reservationRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> findOne(String id) {
        log.debug("Request to get Reservation : {}", id);
        return reservationRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Reservation : {}", id);
        reservationRepository.deleteById(id);
    }
}
