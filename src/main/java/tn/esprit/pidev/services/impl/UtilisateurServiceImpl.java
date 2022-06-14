package tn.esprit.pidev.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Utilisateur;
import tn.esprit.pidev.repository.UtilisateurRepository;
import tn.esprit.pidev.services.api.UtilisateurService;

import java.util.List;
import java.util.Optional;
@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public List<Utilisateur> retrieveAllUtilisateurs() {
        return (List<Utilisateur>) utilisateurRepository.findAll();
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur retrieveUtilisateur(Long id) {
        return utilisateurRepository.findById(id).get();
    }
    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return utilisateur;
    }
}
