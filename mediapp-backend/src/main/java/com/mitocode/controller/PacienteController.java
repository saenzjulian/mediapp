package com.mitocode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
	
	@Autowired
	private IPacienteService service;
	
	@GetMapping
	public ResponseEntity<List<Paciente>> list(){
		List<Paciente> lista = service.listar();
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> listarPorId(@PathVariable("id") Integer id) {
		Paciente pac = service.listarPorId(id); 
		return new ResponseEntity<Paciente>(pac, HttpStatus.OK);
	}
	
	/**
	 * @RequestBody -> para cambiar el tipo de información de JSON a Java y viceversa si es necesario
	 */
	@PostMapping
	public ResponseEntity<Paciente> registrar(@RequestBody Paciente p){
		Paciente pac = service.registrar(p);
		return new ResponseEntity<Paciente>(pac, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Paciente> modificar(@RequestBody Paciente p) throws Exception{
		Paciente pac = service.registrar(p);
		return new ResponseEntity<Paciente>(pac, HttpStatus.CREATED);
	}

}
