package com.franklin.examen.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.franklin.examen.entity.Evento;

public interface EventoService {
	public List<Evento> findAll(Pageable page);
	public List<Evento> finByNombre(String nombre,Pageable page);
	public Evento findById(int id);
	public Evento save(Evento cliente);
	public void delete(int id);
	public Evento findAll();
}
