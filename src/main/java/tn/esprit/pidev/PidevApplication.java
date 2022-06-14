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
public class PidevApplication implements CommandLineRunner {

	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(PidevApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Dummy data
		Sujet s = new Sujet("Sujet label");
		Utilisateur u = new Utilisateur("abc", "efg");

		Article a = new Article();
		//a.setSujet(s);
		//a.setUtilisateur(u);
		a.setNom_article("Nom article behi");
		a.setTexte("Text behi barcha");
		Article x = articleRepository.save(a);

	}
}
