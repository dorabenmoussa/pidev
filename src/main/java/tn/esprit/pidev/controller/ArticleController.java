package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Article;
import tn.esprit.pidev.repository.ArticleRepository;
import tn.esprit.pidev.services.api.ArticleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ArticleController {


    @Autowired
    private ArticleService articleService;


    @GetMapping("/getAllArticles")
    @ResponseBody
    public List<Article> getArticles() {

        return articleService.retrieveAllArticles();
    }

    @GetMapping("/retrieveArticle/{id}")
    @ResponseBody
    public Article retrieveArticle(@PathVariable("id") Long id) {
    return articleService.retrieveArticle(id);
}

    @PostMapping("/addArticle")
    @ResponseBody
    public Article addArticle(@RequestBody Article article)
    {

     return  articleService.ajouterArticle(article);
    }

     @DeleteMapping("/removeArticle/{id}")
     @ResponseBody
    public void supprimerArticle(@PathVariable("id") Long id){
        articleService.deleteArticle(id);
     }
     @PutMapping("/modifyArticle")
    @ResponseBody
    public Article modifierArticle(@RequestBody Article article){
         return  articleService.updateArticle(article);
     }


}
