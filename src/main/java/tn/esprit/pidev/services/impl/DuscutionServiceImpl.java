package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Duscution;
import tn.esprit.pidev.repository.DuscutionRepository;
import tn.esprit.pidev.services.api.DuscutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Duscution}.
 */
@Service
@Transactional
public class DuscutionServiceImpl implements DuscutionService {

    private final Logger log = LoggerFactory.getLogger(DuscutionServiceImpl.class);

    private final DuscutionRepository duscutionRepository;

    public DuscutionServiceImpl(DuscutionRepository duscutionRepository) {
        this.duscutionRepository = duscutionRepository;
    }

    @Override
    public Duscution save(Duscution duscution) {
        log.debug("Request to save Duscution : {}", duscution);
        return duscutionRepository.save(duscution);
    }

    @Override
    public Duscution update(Duscution duscution) {
        log.debug("Request to save Duscution : {}", duscution);
        return duscutionRepository.save(duscution);
    }

    @Override
    public Optional<Duscution> partialUpdate(Duscution duscution) {
        log.debug("Request to partially update Duscution : {}", duscution);

        return duscutionRepository
            .findById(duscution.getId())
            .map(existingDuscution -> {
                return existingDuscution;
            })
            .map(duscutionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Duscution> findAll() {
        log.debug("Request to get all Duscutions");
        return duscutionRepository.findAllWithEagerRelationships();
    }

    public Page<Duscution> findAllWithEagerRelationships(Pageable pageable) {
        return duscutionRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Duscution> findOne(Long id) {
        log.debug("Request to get Duscution : {}", id);
        return duscutionRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Duscution : {}", id);
        duscutionRepository.deleteById(id);
    }
}
