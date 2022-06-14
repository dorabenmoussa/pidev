package tn.esprit.pidev.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Commentaire;
import tn.esprit.pidev.repository.CommentaireRepository;
import tn.esprit.pidev.services.api.CommentaireService;

import java.util.List;
@Service
public class CommentaireServiceImpl   implements CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Override
    public List<Commentaire> retrieveAllCommentaires() {
        return (List<Commentaire>) commentaireRepository.findAll();
    }

    @Override
    public void deleteCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }

    @Override
    public Commentaire updateCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);

    }

    @Override
    public Commentaire retrieveCommentaire(Long id) {
        return commentaireRepository.findById(id).get();
    }
    @Override
    public Commentaire ajouterCommentaire(Commentaire commentaire) {
        commentaireRepository.save(commentaire);
        return commentaire;
    }
}
