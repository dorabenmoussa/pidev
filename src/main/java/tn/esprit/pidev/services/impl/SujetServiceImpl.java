package tn.esprit.pidev.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Sujet;
import tn.esprit.pidev.repository.SujetRepository;
import tn.esprit.pidev.services.api.SujetService;

import java.util.List;
import java.util.Optional;
@Service
public class SujetServiceImpl implements SujetService {
    @Autowired
    private SujetRepository sujetRepository;
    @Override
    public List<Sujet> retrieveAllSujets() {
        return (List<Sujet>) sujetRepository.findAll();
    }

    @Override
    public void deleteSujet(Long id) {
        sujetRepository.deleteById(id);
    }

    @Override
    public Sujet updateSujet(Sujet sujet) {
        return sujetRepository.save(sujet);
    }

    @Override
    public Sujet retrieveSujet(Long id) {
        return sujetRepository.findById(id).get();
    }
    @Override
    public Sujet ajouterSujet(Sujet sujet) {
        sujetRepository.save(sujet);
        return sujet;
    }
}
