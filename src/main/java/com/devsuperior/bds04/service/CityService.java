package com.devsuperior.bds04.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		return cityRepository.findAll().stream().map(CityDTO::new).sorted((o1, o2) -> o1.getName().compareTo(o2.getName())).toList();
	}

	@Transactional
	public CityDTO insert(CityDTO dto) {
		return new CityDTO(cityRepository.save(new City(dto.getId(), dto.getName())));
	}

}
