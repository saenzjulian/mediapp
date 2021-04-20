package com.mitocode.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	
	// Pongo la llave primaria no incrementar por si a futuro paso un script para poblar
	@Id
	private Integer idMenu;

	// Lo declaro como String porque desde Angular solo voy a llamar el nombre del ícono de Angular Material
	@Column(name = "icono", length = 20)
	private String icono;

	@Column(name = "nombre", length = 20)
	private String nombre;

	@Column(name = "url", length = 50)
	private String url;
	
	/**
	 * Esta es una manera paralela cuando en la base de datos hay una relación ManyToMany
	 * es decir, cumple la misma función que la clase con las llaves foraneas que sumadas dan compuesta
	 */	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "menu_rol", 
				joinColumns = @JoinColumn(name = "id_menu", referencedColumnName = "idMenu"), 
				inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
	private List<Rol> roles;

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	
	
}