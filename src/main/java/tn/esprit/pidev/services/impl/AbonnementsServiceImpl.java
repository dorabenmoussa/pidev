package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Abonnements;
import tn.esprit.pidev.repository.AbonnementsRepository;
import tn.esprit.pidev.services.api.AbonnementsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Abonnements}.
 */
@Service
@Transactional
public class AbonnementsServiceImpl implements AbonnementsService {

    private final Logger log = LoggerFactory.getLogger(AbonnementsServiceImpl.class);

    private final AbonnementsRepository abonnementsRepository;

    public AbonnementsServiceImpl(AbonnementsRepository abonnementsRepository) {
        this.abonnementsRepository = abonnementsRepository;
    }

    @Override
    public Abonnements save(Abonnements abonnements) {
        log.debug("Request to save Abonnements : {}", abonnements);
        return abonnementsRepository.save(abonnements);
    }

    @Override
    public Abonnements update(Abonnements abonnements) {
        log.debug("Request to save Abonnements : {}", abonnements);
        return abonnementsRepository.save(abonnements);
    }

    @Override
    public Optional<Abonnements> partialUpdate(Abonnements abonnements) {
        log.debug("Request to partially update Abonnements : {}", abonnements);

        return abonnementsRepository
            .findById(abonnements.getId())
            .map(existingAbonnements -> {
                if (abonnements.getAbonnementName() != null) {
                    existingAbonnements.setAbonnementName(abonnements.getAbonnementName());
                }
                if (abonnements.getDateDeb() != null) {
                    existingAbonnements.setDateDeb(abonnements.getDateDeb());
                }
                if (abonnements.getDateFin() != null) {
                    existingAbonnements.setDateFin(abonnements.getDateFin());
                }

                return existingAbonnements;
            })
            .map(abonnementsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Abonnements> findAll() {
        log.debug("Request to get all Abonnements");
        return abonnementsRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Abonnements> findOne(Long id) {
        log.debug("Request to get Abonnements : {}", id);
        return abonnementsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Abonnements : {}", id);
        abonnementsRepository.deleteById(id);
    }
}
