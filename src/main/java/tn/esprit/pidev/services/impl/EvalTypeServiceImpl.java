package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.EvalType;
import tn.esprit.pidev.repository.EvalTypeRepository;
import tn.esprit.pidev.services.api.EvalTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link EvalType}.
 */
@Service
@Transactional
public class EvalTypeServiceImpl implements EvalTypeService {

    private final Logger log = LoggerFactory.getLogger(EvalTypeServiceImpl.class);

    private final EvalTypeRepository evalTypeRepository;

    public EvalTypeServiceImpl(EvalTypeRepository evalTypeRepository) {
        this.evalTypeRepository = evalTypeRepository;
    }

    @Override
    public EvalType save(EvalType evalType) {
        log.debug("Request to save EvalType : {}", evalType);
        return evalTypeRepository.save(evalType);
    }

    @Override
    public EvalType update(EvalType evalType) {
        log.debug("Request to save EvalType : {}", evalType);
        return evalTypeRepository.save(evalType);
    }

    @Override
    public Optional<EvalType> partialUpdate(EvalType evalType) {
        log.debug("Request to partially update EvalType : {}", evalType);

        return evalTypeRepository
            .findById(evalType.getId())
            .map(existingEvalType -> {
                if (evalType.getNom() != null) {
                    existingEvalType.setNom(evalType.getNom());
                }
                if (evalType.getDesc() != null) {
                    existingEvalType.setDesc(evalType.getDesc());
                }

                return existingEvalType;
            })
            .map(evalTypeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EvalType> findAll() {
        log.debug("Request to get all EvalTypes");
        return evalTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EvalType> findOne(Long id) {
        log.debug("Request to get EvalType : {}", id);
        return evalTypeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete EvalType : {}", id);
        evalTypeRepository.deleteById(id);
    }
}
