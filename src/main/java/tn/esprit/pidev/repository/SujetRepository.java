package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Sujet;
import tn.esprit.pidev.entities.Utilisateur;

@Repository
public interface SujetRepository extends CrudRepository<Sujet, Long> {
}
