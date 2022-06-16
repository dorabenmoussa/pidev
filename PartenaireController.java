package tn.esprit.pidev.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.PartenaireRequestDto;
import dto.PartenaireResponseDto;
import tn.esprit.pidev.services.api.PartenaireService;

@RestController
@RequestMapping("partenaire")
public class PartenaireController {
	
	private PartenaireService partenaireService;
	
	
public PartenaireController(PartenaireService partenaireService) {
		
		this.partenaireService = partenaireService;
	}

@GetMapping("")
public List<PartenaireResponseDto> getPartenaire() {
	return partenaireService.findAll();
}

@PostMapping("")
public PartenaireResponseDto save(@RequestBody() PartenaireRequestDto partenaireRequestDto) {
	return partenaireService.save(partenaireRequestDto);
			}
}
