package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Utilisateur;
@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
}
