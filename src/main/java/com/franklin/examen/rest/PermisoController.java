package com.franklin.examen.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.franklin.examen.entity.Categoria;
import com.franklin.examen.entity.Permiso;
import com.franklin.examen.service.CategoriaService;
import com.franklin.examen.service.PermisoService;
import com.franklin.examen.util.WrapperResponse;
import com.franklin.examen.dto.CategoriaDto;
import com.franklin.examen.dto.PermisoDto;
import com.franklin.examen.converter.CategoriaConverter;
import com.franklin.examen.converter.PermisoConverter;

@RestController
@RequestMapping("/v1/permisos")
public class PermisoController {
	@Autowired
	private PermisoService service;
	
	@Autowired
	private PermisoConverter converter;
	
	@GetMapping
	public ResponseEntity<List<PermisoDto>> findAll(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);				
		List<PermisoDto> categorias = converter.fromEntity(service.findAll(page));
		
		return new WrapperResponse(true, "success", categorias).createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<PermisoDto> create (@RequestBody PermisoDto permiso){
		Permiso permisoEntity=converter.fromDTO(permiso);
		PermisoDto registro = converter.fromEntity(service.save(permisoEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PermisoDto> update(@PathVariable("id") int id,@RequestBody PermisoDto permiso){
		Permiso permisoEntity=converter.fromDTO(permiso);
		PermisoDto registro = converter.fromEntity(service.save(permisoEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PermisoDto> findById(@PathVariable("id") int id){
		PermisoDto registro=converter.fromEntity(service.findById(id));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
	
	
	
	
}
