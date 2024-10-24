package com.franklin.examen.converter;

import org.springframework.stereotype.Component;

import com.franklin.examen.dto.EventoDto;
import com.franklin.examen.entity.Evento;

@Component
public class EventoConverter extends AbstractConverter<Evento, EventoDto>{
	@Override
	public EventoDto fromEntity(Evento entity) {
		if(entity==null) return null;
		return EventoDto.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.build();
	}

	@Override
	public Evento fromDTO(EventoDto dto) {
		if(dto==null) return null;
		return Evento.builder()
				.id((long) dto.getId())
				.nombre(dto.getNombre())
				.build();
	}

}
