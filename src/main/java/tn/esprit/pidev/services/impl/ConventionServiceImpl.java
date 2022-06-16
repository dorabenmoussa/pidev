package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Convention;
import tn.esprit.pidev.repository.ConventionRepository;
import tn.esprit.pidev.services.api.ConventionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Convention}.
 */
@Service
@Transactional
public class ConventionServiceImpl implements ConventionService {

    private final Logger log = LoggerFactory.getLogger(ConventionServiceImpl.class);

    private final ConventionRepository conventionRepository;

    public ConventionServiceImpl(ConventionRepository conventionRepository) {
        this.conventionRepository = conventionRepository;
    }

    @Override
    public Convention save(Convention convention) {
        log.debug("Request to save Convention : {}", convention);
        return conventionRepository.save(convention);
    }

    @Override
    public Convention update(Convention convention) {
        log.debug("Request to save Convention : {}", convention);
        return conventionRepository.save(convention);
    }

    @Override
    public Optional<Convention> partialUpdate(Convention convention) {
        log.debug("Request to partially update Convention : {}", convention);

        return conventionRepository
            .findById(convention.getId())
            .map(existingConvention -> {
                if (convention.getDateDebConvention() != null) {
                    existingConvention.setDateDebConvention(convention.getDateDebConvention());
                }
                if (convention.getDateFinConvention() != null) {
                    existingConvention.setDateFinConvention(convention.getDateFinConvention());
                }
                if (convention.getRenouvelable() != null) {
                    existingConvention.setRenouvelable(convention.getRenouvelable());
                }
                if (convention.getDureeRenouvellement() != null) {
                    existingConvention.setDureeRenouvellement(convention.getDureeRenouvellement());
                }

                return existingConvention;
            })
            .map(conventionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Convention> findAll() {
        log.debug("Request to get all Conventions");
        return conventionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Convention> findOne(Long id) {
        log.debug("Request to get Convention : {}", id);
        return conventionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Convention : {}", id);
        conventionRepository.deleteById(id);
    }
}
