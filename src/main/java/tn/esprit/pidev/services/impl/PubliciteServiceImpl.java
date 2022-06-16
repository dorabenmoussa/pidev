package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Publicite;
import tn.esprit.pidev.repository.PubliciteRepository;
import tn.esprit.pidev.services.api.PubliciteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Publicite}.
 */
@Service
@Transactional
public class PubliciteServiceImpl implements PubliciteService {

    private final Logger log = LoggerFactory.getLogger(PubliciteServiceImpl.class);

    private final PubliciteRepository publiciteRepository;

    public PubliciteServiceImpl(PubliciteRepository publiciteRepository) {
        this.publiciteRepository = publiciteRepository;
    }

    @Override
    public Publicite save(Publicite publicite) {
        log.debug("Request to save Publicite : {}", publicite);
        return publiciteRepository.save(publicite);
    }

    @Override
    public Publicite update(Publicite publicite) {
        log.debug("Request to save Publicite : {}", publicite);
        return publiciteRepository.save(publicite);
    }

    @Override
    public Optional<Publicite> partialUpdate(Publicite publicite) {
        log.debug("Request to partially update Publicite : {}", publicite);

        return publiciteRepository
            .findById(publicite.getId())
            .map(existingPublicite -> {
                if (publicite.getDateDebPublicite() != null) {
                    existingPublicite.setDateDebPublicite(publicite.getDateDebPublicite());
                }
                if (publicite.getDateFinPublicite() != null) {
                    existingPublicite.setDateFinPublicite(publicite.getDateFinPublicite());
                }

                return existingPublicite;
            })
            .map(publiciteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publicite> findAll() {
        log.debug("Request to get all Publicites");
        return publiciteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Publicite> findOne(Long id) {
        log.debug("Request to get Publicite : {}", id);
        return publiciteRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Publicite : {}", id);
        publiciteRepository.deleteById(id);
    }
}
