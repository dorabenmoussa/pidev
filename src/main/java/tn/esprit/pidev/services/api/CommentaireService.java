package tn.esprit.pidev.services.api;

import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Article;
import tn.esprit.pidev.entities.Commentaire;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentaireService {
    List<Commentaire> retrieveAllCommentaires();
    void deleteCommentaire( Long id);
    Commentaire updateCommentaire(Commentaire commentaire);


    Commentaire retrieveCommentaire(Long id);

    Commentaire ajouterCommentaire(Commentaire commentaire);
}
