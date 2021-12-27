package com.devsuperior.bds04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repository.CityRepository;
import com.devsuperior.bds04.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAllPaged(Pageable pageable) {
		return eventRepository.findAll(pageable).map(EventDTO::new);
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		return new EventDTO(eventRepository.save(convertDtoToEvent(dto, new Event())));
	}

	private Event convertDtoToEvent(EventDTO dto, Event event) {
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		event.setCity(cityRepository.findById(dto.getCityId()).get());
		return event;
	}
}
