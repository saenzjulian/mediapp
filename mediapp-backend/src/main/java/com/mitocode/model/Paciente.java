package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer idPaciente;
	
	@NotNull
	@Size(min = 3, message = "Longitud del nombre no válido. Mínimo 3")
	@Column(name = "nombres", nullable = false, length = 70)
	private String nombres;
	
	@NotNull
	@Size(min = 3, message = "Longitud del apellido no válido. Mínimo 3")
	@Column(name = "apellidos", nullable = false, length = 70)
	private String apellidos;
	
	@NotNull
	@NotEmpty
	@Size(min = 8, max = 15, message = "DNI debe tener 8 caracteres")
	@Column(name = "dni", nullable = false, length = 15, unique = true)
	private String dni;
	
	@Size(min = 3, max = 150, message = "Dirección debe tener minimo 3 caracteres")
	@Column(name = "direccion", nullable = true, length = 150)
	private String direccion;
	
	@Size(min = 9, max = 20, message = "Telefono debe tener mínimo 9 caracteres")
	@Column(name = "telefono", nullable = true, length = 20)
	private String telefono;
	
	@NotNull
	@Email(message = "Email formato incorrecto")
	//@Pattern(regexp = "aqui_va_la_expresión_regular")
	@Column(name = "email", nullable = true, length = 55)
	private String email;

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
