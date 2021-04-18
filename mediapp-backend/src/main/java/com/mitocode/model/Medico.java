package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // (name = "XYZ") - Si no defino nombre en entidad toma el de la clase (Medico)
@Table(name = "medico") // Nombre que toma la tabla en la BD - Si no se pone toma el de la clase 
public class Medico {
	
	/**
	 * JPQL (Java Persistence Query Language)
	 * JPQL	->	FROM XYZ m WHERE m.idMedico = 1
	 * SQL 	-> 	SELECT * FROM MEDICO m WHERE m.id_medico = 1
	 */
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedico;

	@Column(name = "nombres", nullable = false, length = 70)
	private String nombres;

	@Column(name = "apellidos", nullable = false, length = 70)
	private String apellidos;

	@Column(name = "CMP", nullable = false, length = 12, unique = true)
	private String CMP;

	@Column(name = "foto_url", nullable = true)
	private String fotoUrl;

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCMP() {
		return CMP;
	}

	public void setCMP(String cMP) {
		CMP = cMP;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	
}
