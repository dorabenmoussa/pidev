package tn.esprit.pidev.services.api;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import dto.PartenaireRequestDto;
import dto.PartenaireResponseDto;

public interface PartenaireService {
	
	PartenaireResponseDto save(PartenaireRequestDto partenaireRequestDto);
	
	
	PartenaireResponseDto findById(Long id);
	
	PartenaireResponseDto findByPartenaireName(String partenaireName);
	
	void delete (Long id);
	
	PartenaireResponseDto update(PartenaireRequestDto partenaireRequestDto , Long id);
	
	List<PartenaireResponseDto> findAll();



}
