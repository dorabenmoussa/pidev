package tn.esprit.pidev.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.pidev.entities.Utilisateur;
import tn.esprit.pidev.repository.UtilisateurRepository;
import tn.esprit.pidev.services.api.UtilisateurService;


import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Utilisateur}.
 */
@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        log.debug("Request to save Utilisateur : {}", utilisateur);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {
        log.debug("Request to save Utilisateur : {}", utilisateur);
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> partialUpdate(Utilisateur utilisateur) {
        log.debug("Request to partially update Utilisateur : {}", utilisateur);

        return utilisateurRepository
            .findById(utilisateur.getId())
            .map(existingUtilisateur -> {
                if (utilisateur.getFirstName() != null) {
                    existingUtilisateur.setFirstName(utilisateur.getFirstName());
                }
                if (utilisateur.getLastName() != null) {
                    existingUtilisateur.setLastName(utilisateur.getLastName());
                }
                if (utilisateur.getRole() != null) {
                    existingUtilisateur.setRole(utilisateur.getRole());
                }
                if (utilisateur.getEmail() != null) {
                    existingUtilisateur.setEmail(utilisateur.getEmail());
                }
                if (utilisateur.getPassword() != null) {
                    existingUtilisateur.setPassword(utilisateur.getPassword());
                }
                if (utilisateur.getJobTitle() != null) {
                    existingUtilisateur.setJobTitle(utilisateur.getJobTitle());
                }
                if (utilisateur.getPhone() != null) {
                    existingUtilisateur.setPhone(utilisateur.getPhone());
                }

                return existingUtilisateur;
            })
            .map(utilisateurRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Utilisateur> findAll() {
        log.debug("Request to get all Utilisateurs");
        return utilisateurRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Utilisateur> findOne(Long id) {
        log.debug("Request to get Utilisateur : {}", id);
        return utilisateurRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Utilisateur : {}", id);
        utilisateurRepository.deleteById(id);
    }
}
