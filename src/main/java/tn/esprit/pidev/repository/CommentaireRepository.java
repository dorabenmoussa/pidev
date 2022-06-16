package tn.esprit.pidev.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Article;
import tn.esprit.pidev.entities.Commentaire;
@Repository
public interface CommentaireRepository extends CrudRepository<Commentaire, Long> {
}
