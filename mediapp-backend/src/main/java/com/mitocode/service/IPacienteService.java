package com.mitocode.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mitocode.model.Paciente;

@Service
public interface IPacienteService {
	
	Paciente registrar (Paciente paciente);
	Paciente modificar (Paciente paciente);
	List<Paciente> listar();
	Paciente listarPorId(Integer id);
	void eliminar(Integer id);
}	
