package com.mitocode.service.impl;

import java.util.List; 

import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICRUD;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {
	
	// protected para que lo use la clase pero lo implementen las otras
	protected abstract IGenericRepo<T, ID> getRepo();

	/**
	 * respecto a los métodos REGISTRAR y MODIFICAR.
	 * si le envío una Entidad con un ID no existe  --> Spring lo registra/inserta
	 * si le envío una Entidad con un ID que existe --> Spring lo modifica/actualiza
	 */
	
	@Override
	public T registrar(T t) {
		return getRepo().save(t);
	}

	@Override
	public List<T> listar() {
		return getRepo().findAll();
	}

	@Override
	public T listarPorId(ID id) { 		
		return getRepo().findById(id).orElse(null);
	}

	@Override
	public T modificar(T t) {
		return getRepo().save(t);
	}

	@Override
	public void eliminar(ID id) {
		getRepo().deleteById(id);
		
	}

}
