package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Badges;
import tn.esprit.pidev.repository.BadgesRepository;
import tn.esprit.pidev.services.api.BadgesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Badges}.
 */
@Service
@Transactional
public class BadgesServiceImpl implements BadgesService {

    private final Logger log = LoggerFactory.getLogger(BadgesServiceImpl.class);

    private final BadgesRepository badgesRepository;

    public BadgesServiceImpl(BadgesRepository badgesRepository) {
        this.badgesRepository = badgesRepository;
    }

    @Override
    public Badges save(Badges badges) {
        log.debug("Request to save Badges : {}", badges);
        return badgesRepository.save(badges);
    }

    @Override
    public Badges update(Badges badges) {
        log.debug("Request to save Badges : {}", badges);
        return badgesRepository.save(badges);
    }

    @Override
    public Optional<Badges> partialUpdate(Badges badges) {
        log.debug("Request to partially update Badges : {}", badges);

        return badgesRepository
            .findById(badges.getId())
            .map(existingBadges -> {
                if (badges.getNom() != null) {
                    existingBadges.setNom(badges.getNom());
                }
                if (badges.getDesc() != null) {
                    existingBadges.setDesc(badges.getDesc());
                }

                return existingBadges;
            })
            .map(badgesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Badges> findAll() {
        log.debug("Request to get all Badges");
        return badgesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Badges> findOne(Long id) {
        log.debug("Request to get Badges : {}", id);
        return badgesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Badges : {}", id);
        badgesRepository.deleteById(id);
    }
}
