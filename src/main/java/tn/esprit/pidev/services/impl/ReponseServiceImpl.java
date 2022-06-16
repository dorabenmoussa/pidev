package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Reponse;
import tn.esprit.pidev.repository.ReponseRepository;
import tn.esprit.pidev.services.api.ReponseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Reponse}.
 */
@Service
@Transactional
public class ReponseServiceImpl implements ReponseService {

    private final Logger log = LoggerFactory.getLogger(ReponseServiceImpl.class);

    private final ReponseRepository reponseRepository;

    public ReponseServiceImpl(ReponseRepository reponseRepository) {
        this.reponseRepository = reponseRepository;
    }

    @Override
    public Reponse save(Reponse reponse) {
        log.debug("Request to save Reponse : {}", reponse);
        return reponseRepository.save(reponse);
    }

    @Override
    public Reponse update(Reponse reponse) {
        log.debug("Request to save Reponse : {}", reponse);
        return reponseRepository.save(reponse);
    }

    @Override
    public Optional<Reponse> partialUpdate(Reponse reponse) {
        log.debug("Request to partially update Reponse : {}", reponse);

        return reponseRepository
            .findById(reponse.getId())
            .map(existingReponse -> {
                if (reponse.getNom() != null) {
                    existingReponse.setNom(reponse.getNom());
                }
                if (reponse.getDesc() != null) {
                    existingReponse.setDesc(reponse.getDesc());
                }

                return existingReponse;
            })
            .map(reponseRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reponse> findAll() {
        log.debug("Request to get all Reponses");
        return reponseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reponse> findOne(Long id) {
        log.debug("Request to get Reponse : {}", id);
        return reponseRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reponse : {}", id);
        reponseRepository.deleteById(id);
    }
}
