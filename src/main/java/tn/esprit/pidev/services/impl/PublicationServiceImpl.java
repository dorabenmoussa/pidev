package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Publication;
import tn.esprit.pidev.repository.PublicationRepository;
import tn.esprit.pidev.services.api.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Publication}.
 */
@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {

    private final Logger log = LoggerFactory.getLogger(PublicationServiceImpl.class);

    private final PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Publication save(Publication publication) {
        log.debug("Request to save Publication : {}", publication);
        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(Publication publication) {
        log.debug("Request to save Publication : {}", publication);
        return publicationRepository.save(publication);
    }

    @Override
    public Optional<Publication> partialUpdate(Publication publication) {
        log.debug("Request to partially update Publication : {}", publication);

        return publicationRepository
            .findById(publication.getId())
            .map(existingPublication -> {
                if (publication.getTitle() != null) {
                    existingPublication.setTitle(publication.getTitle());
                }
                if (publication.getDesc() != null) {
                    existingPublication.setDesc(publication.getDesc());
                }
                if (publication.getPubDate() != null) {
                    existingPublication.setPubDate(publication.getPubDate());
                }
                if (publication.getPubUpdateDate() != null) {
                    existingPublication.setPubUpdateDate(publication.getPubUpdateDate());
                }
                if (publication.getStatus() != null) {
                    existingPublication.setStatus(publication.getStatus());
                }
                if (publication.getLikes() != null) {
                    existingPublication.setLikes(publication.getLikes());
                }
                if (publication.getDislikes() != null) {
                    existingPublication.setDislikes(publication.getDislikes());
                }

                return existingPublication;
            })
            .map(publicationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Publication> findAll() {
        log.debug("Request to get all Publications");
        return publicationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Publication> findOne(Long id) {
        log.debug("Request to get Publication : {}", id);
        return publicationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Publication : {}", id);
        publicationRepository.deleteById(id);
    }
}
