package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Paciente;
import com.mitocode.repo.IPacienteRepo;
import com.mitocode.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService{
	
	// Como necesito una instancia (new) y el IoC la tiene la llamo con la anotación
	@Autowired
	private IPacienteRepo repo;
	
	/**
	 * respecto a los métodos REGISTRAR y MODIFICAR.
	 * si le envío un paciente con un ID no existe  --> Spring lo registra/inserta
	 * si le envío un paciente con un ID que existe --> Spring lo modifica/actualiza
	 */

	@Override
	public Paciente registrar(Paciente paciente) {
		return repo.save(paciente);		
	}

	@Override
	public Paciente modificar(Paciente paciente) {
		return repo.save(paciente);	
	}

	@Override
	public List<Paciente> listar() {
		return repo.findAll();
	}

	@Override
	public Paciente listarPorId(Integer id) {
		/**
		 * .findById -> Retorna un Optional porque este metodo
		 * es posible que retorne un null
		 */
		Optional<Paciente> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : new Paciente();
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
		
	}

}
