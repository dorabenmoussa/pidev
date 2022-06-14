package tn.esprit.pidev.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Article;
import tn.esprit.pidev.repository.ArticleRepository;
import tn.esprit.pidev.services.api.ArticleService;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public List<Article> retrieveAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }


    @Override
    public Article updateArticle(Article article) {

        return articleRepository.save(article);
    }

    @Override
    public Article retrieveArticle(Long id) {

        return articleRepository.findById(id).get();
    }

    @Override
    public Article ajouterArticle(Article article) {
     articleRepository.save(article);
     return article;
    }
}
