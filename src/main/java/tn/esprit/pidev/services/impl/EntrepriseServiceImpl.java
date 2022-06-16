package tn.esprit.pidev.services.impl;

import tn.esprit.pidev.entities.Entreprise;
import tn.esprit.pidev.repository.EntrepriseRepository;
import tn.esprit.pidev.services.api.EntrepriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Entreprise}.
 */
@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService {

    private final Logger log = LoggerFactory.getLogger(EntrepriseServiceImpl.class);

    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public Entreprise save(Entreprise entreprise) {
        log.debug("Request to save Entreprise : {}", entreprise);
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise update(Entreprise entreprise) {
        log.debug("Request to save Entreprise : {}", entreprise);
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Optional<Entreprise> partialUpdate(Entreprise entreprise) {
        log.debug("Request to partially update Entreprise : {}", entreprise);

        return entrepriseRepository
            .findById(entreprise.getId())
            .map(existingEntreprise -> {
                if (entreprise.getNom() != null) {
                    existingEntreprise.setNom(entreprise.getNom());
                }

                return existingEntreprise;
            })
            .map(entrepriseRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entreprise> findAll() {
        log.debug("Request to get all Entreprises");
        return entrepriseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Entreprise> findOne(Long id) {
        log.debug("Request to get Entreprise : {}", id);
        return entrepriseRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Entreprise : {}", id);
        entrepriseRepository.deleteById(id);
    }
}
