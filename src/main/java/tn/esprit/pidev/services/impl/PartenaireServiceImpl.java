package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Partenaire;
import tn.esprit.pidev.repository.PartenaireRepository;
import tn.esprit.pidev.services.api.PartenaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Partenaire}.
 */
@Service
@Transactional
public class PartenaireServiceImpl implements PartenaireService {

    private final Logger log = LoggerFactory.getLogger(PartenaireServiceImpl.class);

    private final PartenaireRepository partenaireRepository;

    public PartenaireServiceImpl(PartenaireRepository partenaireRepository) {
        this.partenaireRepository = partenaireRepository;
    }

    @Override
    public Partenaire save(Partenaire partenaire) {
        log.debug("Request to save Partenaire : {}", partenaire);
        return partenaireRepository.save(partenaire);
    }

    @Override
    public Partenaire update(Partenaire partenaire) {
        log.debug("Request to save Partenaire : {}", partenaire);
        return partenaireRepository.save(partenaire);
    }

    @Override
    public Optional<Partenaire> partialUpdate(Partenaire partenaire) {
        log.debug("Request to partially update Partenaire : {}", partenaire);

        return partenaireRepository
            .findById(partenaire.getId())
            .map(existingPartenaire -> {
                if (partenaire.getPartenaireName() != null) {
                    existingPartenaire.setPartenaireName(partenaire.getPartenaireName());
                }
                if (partenaire.getPartenaireType() != null) {
                    existingPartenaire.setPartenaireType(partenaire.getPartenaireType());
                }
                if (partenaire.getMatriculation() != null) {
                    existingPartenaire.setMatriculation(partenaire.getMatriculation());
                }

                return existingPartenaire;
            })
            .map(partenaireRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> findAll() {
        log.debug("Request to get all Partenaires");
        return partenaireRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Partenaire> findOne(Long id) {
        log.debug("Request to get Partenaire : {}", id);
        return partenaireRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Partenaire : {}", id);
        partenaireRepository.deleteById(id);
    }
}
