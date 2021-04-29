package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Especialidad;
import com.mitocode.service.IEspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
	
	@Autowired
	private IEspecialidadService service;
	
	@GetMapping
	public ResponseEntity<List<Especialidad>> list() throws Exception{
		List<Especialidad> lista = service.listar();
		return new ResponseEntity<List<Especialidad>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Especialidad> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Especialidad obj = service.listarPorId(id); 
		if(obj == null) {
			throw new ModelNotFoundException("ID no encontrado " + id);	
		}
		return new ResponseEntity<Especialidad>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Especialidad> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception{
		Especialidad obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModelNotFoundException("ID no encontrado " + id);
		}
		
		EntityModel<Especialidad> recurso = EntityModel.of(obj);
		
		// localhost:8080/pacientes/4
		/*
		 * Voy a construir un bloque de links que es el recurso de donde saqué la información
		 **** El localhost:8080 viene implicito 
		 * this.getClass es el endpoint que la clase -> /pacientes
		 * .listarPorIdHateoas -> extrae la parte dinamica de la URL, ojo NO lo estoy invocando para que se ejecute
		 */
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdHateoas(id));
		//WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdHateoas(id));
		
		recurso.add(link1.withRel("especialidad-recurso1"));
		//recurso.add(link2.withRel("especialidad-recurso2"));
		
		return recurso;
	}
	
	/**
	 * @RequestBody -> para cambiar el tipo de información de JSON a Java y viceversa si es necesario
	 
	@PostMapping
	public ResponseEntity<Especialidad> registrar(@Valid @RequestBody Especialidad p) throws Exception{
		Especialidad pac = service.registrar(p);
		return new ResponseEntity<Especialidad>(pac, HttpStatus.CREATED);
	}
	*/
	
	@PostMapping
	public ResponseEntity<Especialidad> registrar(@Valid @RequestBody Especialidad p) throws Exception{
		Especialidad obj = service.registrar(p);
		/**
		 * Con el fin de obtener más información del POST REQUEST en la ruta -> localhost:8080/pacientes/id
		 * ServletUriComponentsBuilder.fromCurrentRequest() -> 	"localhost:8080/pacientes"
		 * .path("/{id}")	->	"/{id}" : parte dinámica
		 * .buildAndExpand(pac.getIdPaciente()).toUri(); : Cómo poblar la parte dinámica		 * 
		 */		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEspecialidad()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Especialidad> modificar(@Valid @RequestBody Especialidad p) throws Exception{
		Especialidad obj = service.modificar(p);
		return new ResponseEntity<Especialidad>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Especialidad obj = service.listarPorId(id); 
		if(obj == null) {
			throw new ModelNotFoundException("ID no encontrado " + id);	
		}
		service.eliminar(id); 
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
