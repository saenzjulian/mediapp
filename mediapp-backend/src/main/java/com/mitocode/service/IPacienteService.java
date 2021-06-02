package com.mitocode.service;  

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mitocode.model.Paciente;  

public interface IPacienteService extends ICRUD<Paciente, Integer>{ 
	/**
	 * En el futuro es posible que si quiera poner un Query 
	 * solo para la entidad lo haría aqui con un metodo
	 */
	
	Page<Paciente>listarPageable(Pageable pageable);
	
}	
