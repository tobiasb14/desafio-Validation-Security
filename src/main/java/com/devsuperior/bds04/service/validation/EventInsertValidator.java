package com.devsuperior.bds04.service.validation;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.devsuperior.bds04.controller.exception.FieldMessage;
import com.devsuperior.bds04.dto.EventDTO;

public class EventInsertValidator implements ConstraintValidator<EventInsertValid, EventDTO> {

	@Override
	public void initialize(EventInsertValid ann) {
	}

	@Override
	public boolean isValid(EventDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (dto.getName().isBlank()) {
			list.add(new FieldMessage("name", "Campo requerido"));
		}
		
		if (dto.getDate().isBefore(ChronoLocalDate.from(LocalDate.now()))) {
			list.add(new FieldMessage("date", "A data do evento não pode ser passada"));
		}
		
		if (dto.getCityId() == null) {
			list.add(new FieldMessage("cityId", "Campo requerido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
