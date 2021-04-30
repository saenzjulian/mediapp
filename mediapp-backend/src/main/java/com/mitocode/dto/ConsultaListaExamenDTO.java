package com.mitocode.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.mitocode.model.Consulta;
import com.mitocode.model.Examen;

public class ConsultaListaExamenDTO {
	
	@NotNull
	private Consulta consulta;
	
	@NotNull
	private List<Examen> listExamen;

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Examen> getListExamen() {
		return listExamen;
	}

	public void setListExamen(List<Examen> listExamen) {
		this.listExamen = listExamen;
	}	

}
