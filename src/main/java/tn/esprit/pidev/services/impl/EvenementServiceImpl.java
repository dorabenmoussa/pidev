package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Evenement;
import tn.esprit.pidev.repository.EvenementRepository;
import tn.esprit.pidev.services.api.EvenementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Evenement}.
 */
@Service
@Transactional
public class EvenementServiceImpl implements EvenementService {

    private final Logger log = LoggerFactory.getLogger(EvenementServiceImpl.class);

    private final EvenementRepository evenementRepository;

    public EvenementServiceImpl(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    @Override
    public Evenement save(Evenement evenement) {
        log.debug("Request to save Evenement : {}", evenement);
        return evenementRepository.save(evenement);
    }

    @Override
    public Evenement update(Evenement evenement) {
        log.debug("Request to save Evenement : {}", evenement);
        return evenementRepository.save(evenement);
    }

    @Override
    public Optional<Evenement> partialUpdate(Evenement evenement) {
        log.debug("Request to partially update Evenement : {}", evenement);

        return evenementRepository
            .findById(evenement.getId())
            .map(existingEvenement -> {
                if (evenement.getEvenementName() != null) {
                    existingEvenement.setEvenementName(evenement.getEvenementName());
                }
                if (evenement.getPhoto() != null) {
                    existingEvenement.setPhoto(evenement.getPhoto());
                }
                if (evenement.getDateDeb() != null) {
                    existingEvenement.setDateDeb(evenement.getDateDeb());
                }
                if (evenement.getDateFin() != null) {
                    existingEvenement.setDateFin(evenement.getDateFin());
                }
                if (evenement.getNbrTiket() != null) {
                    existingEvenement.setNbrTiket(evenement.getNbrTiket());
                }

                return existingEvenement;
            })
            .map(evenementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evenement> findAll() {
        log.debug("Request to get all Evenements");
        return evenementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Evenement> findOne(Long id) {
        log.debug("Request to get Evenement : {}", id);
        return evenementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Evenement : {}", id);
        evenementRepository.deleteById(id);
    }
}
