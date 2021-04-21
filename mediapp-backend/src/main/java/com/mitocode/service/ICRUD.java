package com.mitocode.service;

import java.util.List;

public interface ICRUD<T, ID>{
	
	T registrar(T t); 		// Create
	List<T> listar(); 		// Read
	T listarPorId(ID id); 	// Read by Id
	T modificar (T t); 		// Update
	void eliminar(ID id); 	// Delete	

}
