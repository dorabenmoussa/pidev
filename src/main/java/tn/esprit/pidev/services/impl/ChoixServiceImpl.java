package tn.esprit.pidev.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.pidev.entities.Choix;
import tn.esprit.pidev.repository.ChoixRepository;
import tn.esprit.pidev.services.api.ChoixService;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Choix}.
 */
@Service
@Transactional
public class ChoixServiceImpl implements ChoixService {

    private final Logger log = LoggerFactory.getLogger(ChoixServiceImpl.class);

    private final ChoixRepository choixRepository;

    public ChoixServiceImpl(ChoixRepository choixRepository) {
        this.choixRepository = choixRepository;
    }

    @Override
    public Choix save(Choix choix) {
        log.debug("Request to save Choix : {}", choix);
        return choixRepository.save(choix);
    }

    @Override
    public Choix update(Choix choix) {
        log.debug("Request to save Choix : {}", choix);
        return choixRepository.save(choix);
    }

    @Override
    public Optional<Choix> partialUpdate(Choix choix) {
        log.debug("Request to partially update Choix : {}", choix);

        return choixRepository
            .findById(choix.getId())
            .map(existingChoix -> {
                if (choix.getChoix1() != null) {
                    existingChoix.setChoix1(choix.getChoix1());
                }
                if (choix.getChoix2() != null) {
                    existingChoix.setChoix2(choix.getChoix2());
                }
                if (choix.getChoix3() != null) {
                    existingChoix.setChoix3(choix.getChoix3());
                }
                if (choix.getCorrectAnswer() != null) {
                    existingChoix.setCorrectAnswer(choix.getCorrectAnswer());
                }
                if (choix.getNote() != null) {
                    existingChoix.setNote(choix.getNote());
                }
                if (choix.getIsCorrect() != null) {
                    existingChoix.setIsCorrect(choix.getIsCorrect());
                }

                return existingChoix;
            })
            .map(choixRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Choix> findAll() {
        log.debug("Request to get all Choixes");
        return choixRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Choix> findOne(Long id) {
        log.debug("Request to get Choix : {}", id);
        return choixRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Choix : {}", id);
        choixRepository.deleteById(id);
    }
}
