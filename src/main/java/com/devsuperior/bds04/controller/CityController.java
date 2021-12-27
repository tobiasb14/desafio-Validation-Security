package com.devsuperior.bds04.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll() {
		return ResponseEntity.ok(cityService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO dto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(cityService.insert(dto));
	}
}
