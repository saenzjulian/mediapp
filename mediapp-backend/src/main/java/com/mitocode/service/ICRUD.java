package com.mitocode.service;

import java.util.List;

public interface ICRUD<T, ID>{
	
	T registrar(T t) throws Exception; 		// Create
	List<T> listar() throws Exception; 		// Read
	T listarPorId(ID id) throws Exception; 	// Read by Id
	T modificar (T t) throws Exception;		// Update
	void eliminar(ID id) throws Exception; 	// Delete	

}
