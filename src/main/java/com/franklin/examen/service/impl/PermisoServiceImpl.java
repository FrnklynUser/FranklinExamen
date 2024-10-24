package com.franklin.examen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franklin.examen.entity.Categoria;
import com.franklin.examen.entity.Permiso;
import com.franklin.examen.exception.GeneralException;
import com.franklin.examen.exception.NoDataFoundException;
import com.franklin.examen.exception.ValidateException;
import com.franklin.examen.repository.CategoriaRepository;
import com.franklin.examen.repository.PermisoRepository;
import com.franklin.examen.service.CategoriaService;
import com.franklin.examen.service.PermisoService;
import com.franklin.examen.validator.CategoriaValidator;
import com.franklin.examen.validator.PermisoValidator;

@Service
public class PermisoServiceImpl implements PermisoService{
	@Autowired
	private PermisoRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Permiso> findAll(Pageable page) {
		try {
			List<Permiso> registros=repository.findAll(page).toList();
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
	public List<Permiso> finByNombre(String nombre, Pageable page) {
		try {
			List<Permiso> registros=repository.findByNombreContaining(nombre,page);
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
	public Permiso findById(int id) {
		try {
			Permiso registro=repository.findById(id).
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
	public Permiso save(Permiso permiso) {
		try {
			
			PermisoValidator.save(permiso);
			//Nuevo registro
			if (permiso.getId()==0) {
				Permiso nuevo=repository.save(permiso);
				return nuevo;				
			}
			//editar registro
			Permiso registro=repository.findById(permiso.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombre(permiso.getNombre());
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
			Permiso registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
		
	}

}
