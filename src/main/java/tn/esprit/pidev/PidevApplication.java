package tn.esprit.pidev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tn.esprit.pidev.entities.Article;
import tn.esprit.pidev.entities.Sujet;
import tn.esprit.pidev.entities.Utilisateur;
import tn.esprit.pidev.repository.ArticleRepository;

@SpringBootApplication
public class PidevApplication {

	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(PidevApplication.class, args);
	}

}
