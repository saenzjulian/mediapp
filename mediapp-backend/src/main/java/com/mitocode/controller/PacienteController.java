package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private IPacienteService service;
	
	@GetMapping
	public ResponseEntity<List<Paciente>> list() throws Exception{
		List<Paciente> lista = service.listar();
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Paciente obj = service.listarPorId(id); 
		if(obj == null) {
			throw new ModelNotFoundException("ID no encontrado " + id);	
		}
		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Paciente> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception{
		Paciente obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModelNotFoundException("ID no encontrado " + id);
		}
		
		EntityModel<Paciente> recurso = EntityModel.of(obj);
		
		// localhost:8080/pacientes/4
		/*
		 * Voy a construir un bloque de links que es el recurso de donde saqu?? la informaci??n
		 **** El localhost:8080 viene implicito 
		 * this.getClass es el endpoint que la clase -> /pacientes
		 * .listarPorIdHateoas -> extrae la parte dinamica de la URL, ojo NO lo estoy invocando para que se ejecute
		 */
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).listarPorIdHateoas(id));
		//WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).listarPorIdHateoas(id));
		
		recurso.add(link1.withRel("paciente-recurso1"));
		//recurso.add(link2.withRel("paciente-recurso2"));
		
		return recurso;
	}
	
	/**
	 * @RequestBody -> para cambiar el tipo de informaci??n de JSON a Java y viceversa si es necesario
	 
	@PostMapping
	public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente p) throws Exception{
		Paciente pac = service.registrar(p);
		return new ResponseEntity<Paciente>(pac, HttpStatus.CREATED);
	}
	*/
	
	@PostMapping
	public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente p) throws Exception{
		Paciente obj = service.registrar(p);
		/**
		 * Con el fin de obtener m??s informaci??n del POST REQUEST en la ruta -> localhost:8080/pacientes/id
		 * ServletUriComponentsBuilder.fromCurrentRequest() -> 	"localhost:8080/pacientes"
		 * .path("/{id}")	->	"/{id}" : parte din??mica
		 * .buildAndExpand(pac.getIdPaciente()).toUri(); : C??mo poblar la parte din??mica		 * 
		 */		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPaciente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Paciente> modificar(@Valid @RequestBody Paciente p) throws Exception{
		Paciente obj = service.modificar(p);
		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Paciente obj = service.listarPorId(id); 
		if(obj == null) {
			throw new ModelNotFoundException("ID no encontrado " + id);	
		}
		service.eliminar(id); 
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Paciente>> listarPageable(Pageable pageable) throws Exception{
		Page<Paciente> pacientes = service.listarPageable(pageable);
		return new ResponseEntity<Page<Paciente>>(pacientes, HttpStatus.OK);
	}

}
