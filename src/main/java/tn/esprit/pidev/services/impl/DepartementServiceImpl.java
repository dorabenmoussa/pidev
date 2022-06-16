package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Departement;
import tn.esprit.pidev.repository.DepartementRepository;
import tn.esprit.pidev.services.api.DepartementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Departement}.
 */
@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    private final Logger log = LoggerFactory.getLogger(DepartementServiceImpl.class);

    private final DepartementRepository departementRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    @Override
    public Departement save(Departement departement) {
        log.debug("Request to save Departement : {}", departement);
        return departementRepository.save(departement);
    }

    @Override
    public Departement update(Departement departement) {
        log.debug("Request to save Departement : {}", departement);
        return departementRepository.save(departement);
    }

    @Override
    public Optional<Departement> partialUpdate(Departement departement) {
        log.debug("Request to partially update Departement : {}", departement);

        return departementRepository
            .findById(departement.getId())
            .map(existingDepartement -> {
                if (departement.getNom() != null) {
                    existingDepartement.setNom(departement.getNom());
                }
                if (departement.getDesc() != null) {
                    existingDepartement.setDesc(departement.getDesc());
                }

                return existingDepartement;
            })
            .map(departementRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Departement> findAll() {
        log.debug("Request to get all Departements");
        return departementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Departement> findOne(Long id) {
        log.debug("Request to get Departement : {}", id);
        return departementRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Departement : {}", id);
        departementRepository.deleteById(id);
    }
}
