package tn.esprit.pidev.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.pidev.entities.Evaluation;
import tn.esprit.pidev.repository.EvaluationRepository;

import tn.esprit.pidev.services.api.EvaluationService;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Evaluation}.
 */
@Service
@Transactional
public class EvaluationServiceImpl implements EvaluationService {

    private final Logger log = LoggerFactory.getLogger(EvaluationServiceImpl.class);

    private final EvaluationRepository evaluationRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        log.debug("Request to save Evaluation : {}", evaluation);
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation update(Evaluation evaluation) {
        log.debug("Request to save Evaluation : {}", evaluation);
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Optional<Evaluation> partialUpdate(Evaluation evaluation) {
        log.debug("Request to partially update Evaluation : {}", evaluation);

        return evaluationRepository
            .findById(evaluation.getId())
            .map(existingEvaluation -> {
                if (evaluation.getDescription() != null) {
                    existingEvaluation.setDescription(evaluation.getDescription());
                }
                if (evaluation.getEvaluationDate() != null) {
                    existingEvaluation.setEvaluationDate(evaluation.getEvaluationDate());
                }
                if (evaluation.getNoteFinal() != null) {
                    existingEvaluation.setNoteFinal(evaluation.getNoteFinal());
                }

                return existingEvaluation;
            })
            .map(evaluationRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evaluation> findAll() {
        log.debug("Request to get all Evaluations");
        return evaluationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Evaluation> findOne(Long id) {
        log.debug("Request to get Evaluation : {}", id);
        return evaluationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Evaluation : {}", id);
        evaluationRepository.deleteById(id);
    }
}
