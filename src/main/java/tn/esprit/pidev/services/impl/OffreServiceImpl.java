package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Offre;
import tn.esprit.pidev.repository.OffreRepository;
import tn.esprit.pidev.services.api.OffreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Offre}.
 */
@Service
@Transactional
public class OffreServiceImpl implements OffreService {

    private final Logger log = LoggerFactory.getLogger(OffreServiceImpl.class);

    private final OffreRepository offreRepository;

    public OffreServiceImpl(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    @Override
    public Offre save(Offre offre) {
        log.debug("Request to save Offre : {}", offre);
        return offreRepository.save(offre);
    }

    @Override
    public Offre update(Offre offre) {
        log.debug("Request to save Offre : {}", offre);
        return offreRepository.save(offre);
    }

    @Override
    public Optional<Offre> partialUpdate(Offre offre) {
        log.debug("Request to partially update Offre : {}", offre);

        return offreRepository
            .findById(offre.getId())
            .map(existingOffre -> {
                if (offre.getDateDebOffre() != null) {
                    existingOffre.setDateDebOffre(offre.getDateDebOffre());
                }
                if (offre.getDateFinOffre() != null) {
                    existingOffre.setDateFinOffre(offre.getDateFinOffre());
                }
                if (offre.getNbreLimit() != null) {
                    existingOffre.setNbreLimit(offre.getNbreLimit());
                }
                if (offre.getTypeOffre() != null) {
                    existingOffre.setTypeOffre(offre.getTypeOffre());
                }

                return existingOffre;
            })
            .map(offreRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Offre> findAll() {
        log.debug("Request to get all Offres");
        return offreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Offre> findOne(Long id) {
        log.debug("Request to get Offre : {}", id);
        return offreRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Offre : {}", id);
        offreRepository.deleteById(id);
    }
}
