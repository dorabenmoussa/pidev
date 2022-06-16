package tn.esprit.pidev.services.api;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Sujet;

import java.util.List;
import java.util.Optional;

@Service
public interface SujetService {

    List<Sujet> retrieveAllSujets();
    void deleteSujet( Long id);
    Sujet updateSujet(Sujet sujet);
   Sujet retrieveSujet(Long id);

    Sujet ajouterSujet(Sujet sujet);
}
