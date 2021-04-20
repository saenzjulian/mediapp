package com.mitocode.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Esta clase solo se usa para la llave primaria compuesta
 * porque la clase principal es ConsultaExamen que es la que tiene Getters an Setters
 */
@Embeddable
public class ConsultaExamenPK {
	
	/**
	 * Siendo [idConsulta] e [idExamen] llaves primarias es sus respectivas tablas
	 * pero en la tabla ConsultaExamen que nace de la relación de las mismas
	 * ya se relacionan ManyToMany, la suma de esas dos llaves foraneas da como 
	 * resultado una llave primaria compuesta. 
	 * [idConsulta(FK) + idExamen(FK)](PK)	 * 
	 */
	
	@ManyToOne
	@JoinColumn(name = "id_consulta", nullable = false)
	private Consulta consulta;
	
	@ManyToOne
	@JoinColumn(name = "id_examen", nullable = false)
	private Examen examen;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consulta == null) ? 0 : consulta.hashCode());
		result = prime * result + ((examen == null) ? 0 : examen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaExamenPK other = (ConsultaExamenPK) obj;
		if (consulta == null) {
			if (other.consulta != null)
				return false;
		} else if (!consulta.equals(other.consulta))
			return false;
		if (examen == null) {
			if (other.examen != null)
				return false;
		} else if (!examen.equals(other.examen))
			return false;
		return true;
	}
	
	
	

}
