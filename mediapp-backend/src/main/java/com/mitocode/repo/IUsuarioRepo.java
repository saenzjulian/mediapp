package com.mitocode.repo;

import com.mitocode.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer>  {
	
	// Saber cual es el usuario que está inciando sesión
	//from usuario where username = ?
	Usuario findOneByUsername(String username);	
}