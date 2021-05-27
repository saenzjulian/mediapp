package com.mitocode.service;  

import java.time.LocalDateTime;
import java.util.List;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;  

public interface IConsultaService extends ICRUD<Consulta, Integer>{ 
	/**
	 * En el futuro es posible que si quiera poner un Query 
	 * solo para la entidad lo haría aqui con un metodo
	 */
	Consulta registroTransaccional(ConsultaListaExamenDTO dto);
	
	List<Consulta> buscar(FiltroConsultaDTO filtro);
	
	List<Consulta> buscarFecha(LocalDateTime fecha);
}	
