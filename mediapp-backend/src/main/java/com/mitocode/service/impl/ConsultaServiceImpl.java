package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IConsultaService;

@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta, Integer> implements IConsultaService{
	
	// Como necesito una instancia (new) y el IoC la tiene la llamo con la anotación
	@Autowired
	private IConsultaRepo repo;

	@Override
	protected IGenericRepo<Consulta, Integer> getRepo() {
		return repo;
	}

	@Override
	public Consulta registroTransaccional(Consulta consulta) {
		// Insertar la consulta para ontener llave primaria
		// Insertar el detalle consulta usando la llave primaria anterior
		consulta.getDetalleConsulta().forEach(detalle -> detalle.setConsulta(consulta));
		return repo.save(consulta); 
	}

}
