package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Article;
import tn.esprit.pidev.entities.Commentaire;
import tn.esprit.pidev.services.api.CommentaireService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentaireController {
    @Autowired
    CommentaireService commentaireService;
    @GetMapping("/getaAllCommentaires")
    @ResponseBody
    public List<Commentaire> getCommentaires() {

        return commentaireService.retrieveAllCommentaires();
    }
    @GetMapping("/retrieveCommentaire/{id}")
    @ResponseBody
    public Commentaire retrieveCommentaire(@PathVariable("id") Long id) {
        return commentaireService.retrieveCommentaire(id);
    }
    @PostMapping("/addCommentaire")
    @ResponseBody
    public Commentaire addCommentaire(@RequestBody Commentaire commentaire)
    {

        return  commentaireService.ajouterCommentaire(commentaire);
    }
    @DeleteMapping("/removeCommentaire/{id}")
    @ResponseBody
    public void supprimerCommentaire(@PathVariable("id") Long id){
        commentaireService.deleteCommentaire(id);
    }
    @PutMapping("/modifyCommentaire")
    @ResponseBody
    public Commentaire modifierCommentaire(@RequestBody Commentaire commentaire){
        return  commentaireService.updateCommentaire(commentaire);
    }


}
