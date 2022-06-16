package tn.esprit.pidev.services.api;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

@Service
public interface UtilisateurService {

    List<Utilisateur> retrieveAllUtilisateurs();

    void deleteUtilisateur(Long id);

    Utilisateur updateUtilisateur(Utilisateur utilisateur);

   Utilisateur retrieveUtilisateur(Long id);

    Utilisateur ajouterUtilisateur(Utilisateur utilisateur);
}
