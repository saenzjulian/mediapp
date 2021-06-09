package com.mitocode.repo;

import com.mitocode.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer>  {
	
	// Saber cual es el usuario que está inciando sesión
	//from usuario where username = ?
	/**
	 * El fin de este metodo es identificar que usuario está inciando sesión
	 * from usuario where username = ?
	 * con sé que la consulta me va a retornar un solo usuario uso findOneBy
	 * si por el contrario me retorna una lista usaría findby
	 */
	Usuario findOneByUsername(String username);	
}