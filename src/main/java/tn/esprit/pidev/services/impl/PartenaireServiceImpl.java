package tn.esprit.pidev.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.ThenOrElseActionBase;
import dto.PartenaireRequestDto;
import dto.PartenaireResponseDto;
import tn.esprit.pidev.entities.Partenaire;
import tn.esprit.pidev.repository.PartenaireDao;
import tn.esprit.pidev.services.api.PartenaireService;


@Service()
public class PartenaireServiceImpl implements PartenaireService{
	
	
	@Override
	public PartenaireResponseDto update(PartenaireRequestDto partenaireRequestDto, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartenaireResponseDto> findAll() {
		return partenaireDao.findAll().stream().map(p ->modelMapper.map(p, PartenaireResponseDto.class) )
				.collect(Collectors.toList());
	}

	private PartenaireDao partenaireDao;
	private ModelMapper modelMapper;
	

	public PartenaireServiceImpl(PartenaireDao partenaireDao, ModelMapper modelMapper) {
	
		this.partenaireDao = partenaireDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public PartenaireResponseDto save(PartenaireRequestDto partenaireRequestDto) {
	
		Partenaire partenaire=modelMapper.map(partenaireRequestDto,Partenaire.class);
		Partenaire save=partenaireDao.save(partenaire);
		return modelMapper.map(save, PartenaireResponseDto.class);
	}

	@Override
	public PartenaireResponseDto findById(Long id) {
		 Partenaire partenaire=partenaireDao.findById(id).orElseThrow(() -> new RuntimeException("Partenaire Not Found"));
		 return modelMapper.map(partenaire, PartenaireResponseDto.class); 
		 
		 
		 
	}

	@Override
	public PartenaireResponseDto findByPartenaireName(String partenaireName) {
		Partenaire partenaire=partenaireDao.findByPartenaireName(partenaireName);
		return modelMapper.map(partenaire, PartenaireResponseDto.class);
	}

	@Override
	public void delete(Long id) {
		
		
		partenaireDao.deleteById(id);
		
	}

	//@Override
	//public PartenaireResponseDto update(PartenaireRequestDto partenaireRequestDto, Long id) throws NotFoundException; {
	//	Optional<Partenaire>  partenaire=partenaireDao.findById(id);
		//if (partenaire.isPresent()) {
		//	Partenaire partenaire2=modelMapper.map(partenaireRequestDto, Partenaire.class);
			//partenaire2.setId(id);
			//Partenaire updated= partenaireDao.save(partenaire2);
			//return modelMapper.map(updated, PartenaireResponseDto.class);
					
					
		//}
		//else {
			
		//}
	//}
	

}
