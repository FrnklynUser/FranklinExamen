package com.franklin.examen.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franklin.examen.entity.Evento;
import com.franklin.examen.entity.Permiso;
import com.franklin.examen.exception.GeneralException;
import com.franklin.examen.exception.NoDataFoundException;
import com.franklin.examen.exception.ValidateException;
import com.franklin.examen.repository.EventoRepository;
import com.franklin.examen.repository.PermisoRepository;
import com.franklin.examen.service.EventoService;
import com.franklin.examen.service.PermisoService;
import com.franklin.examen.validator.EventoValidator;
import com.franklin.examen.validator.PermisoValidator;

@Service
public class EventoServiceImpl implements EventoService{
	@Autowired
	private EventoRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Evento> findAll(Pageable page) {
		try {
			List<Evento> registros=repository.findAll(page).toList();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Evento> finByNombre(String nombre, Pageable page) {
		try {
			List<Evento> registros=repository.findByNombreContaining(nombre,page);
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Evento findById(int id) {
		try {
			Evento registro=repository.findById(id).
					orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional
	public Evento save(Evento cliente) {
		try {
			
			EventoValidator.save(cliente);
			//Nuevo registro
			if (cliente.getId()==0) {
				Evento nuevo=repository.save(cliente);
				return nuevo;				
			}
			//editar registro
			Evento registro=repository.findById(cliente.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombre(cliente.getNombre());
			repository.save(registro);
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Evento registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
		
	}

	@Override
	public Evento findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
