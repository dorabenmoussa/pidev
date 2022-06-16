package tn.esprit.pidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pidev.entities.Partenaire;



@Repository
public interface PartenaireDao extends JpaRepository<Partenaire, Long>{

	
	Partenaire findByPartenaireName(String partenaireName);
	
}
