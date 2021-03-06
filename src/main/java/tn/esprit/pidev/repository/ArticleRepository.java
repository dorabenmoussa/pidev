package tn.esprit.pidev.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.entities.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {


}
