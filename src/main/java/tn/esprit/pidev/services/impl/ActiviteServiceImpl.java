package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Activite;
import tn.esprit.pidev.repository.ActiviteRepository;
import tn.esprit.pidev.services.api.ActiviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Activite}.
 */
@Service
@Transactional
public class ActiviteServiceImpl implements ActiviteService {

    private final Logger log = LoggerFactory.getLogger(ActiviteServiceImpl.class);

    private final ActiviteRepository activiteRepository;

    public ActiviteServiceImpl(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    @Override
    public Activite save(Activite activite) {
        log.debug("Request to save Activite : {}", activite);
        return activiteRepository.save(activite);
    }

    @Override
    public Activite update(Activite activite) {
        log.debug("Request to save Activite : {}", activite);
        return activiteRepository.save(activite);
    }

    @Override
    public Optional<Activite> partialUpdate(Activite activite) {
        log.debug("Request to partially update Activite : {}", activite);

        return activiteRepository
            .findById(activite.getId())
            .map(existingActivite -> {
                if (activite.getActiviteName() != null) {
                    existingActivite.setActiviteName(activite.getActiviteName());
                }
                if (activite.getPhoto() != null) {
                    existingActivite.setPhoto(activite.getPhoto());
                }
                if (activite.getDateDeb() != null) {
                    existingActivite.setDateDeb(activite.getDateDeb());
                }
                if (activite.getDateFin() != null) {
                    existingActivite.setDateFin(activite.getDateFin());
                }

                return existingActivite;
            })
            .map(activiteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Activite> findAll() {
        log.debug("Request to get all Activites");
        return activiteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Activite> findOne(Long id) {
        log.debug("Request to get Activite : {}", id);
        return activiteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Activite : {}", id);
        activiteRepository.deleteById(id);
    }
}
