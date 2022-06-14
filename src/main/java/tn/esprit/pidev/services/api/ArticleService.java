package tn.esprit.pidev.services.api;

import tn.esprit.pidev.entities.Article;

import java.util.List;
import java.util.Optional;

//@Service
public interface ArticleService {

    List<Article> retrieveAllArticles();
    void deleteArticle( Long id);
    Article updateArticle(Article article);
    Article retrieveArticle(Long id);

    Article ajouterArticle(Article article);

}
