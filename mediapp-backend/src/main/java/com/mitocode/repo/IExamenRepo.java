package com.mitocode.repo;

import com.mitocode.model.Examen;

/*
 * Esta interfaz tiene acceso directo a la base de datos
 * porque tiene herencias de JpaRepository y esta ya tiene toda
 * la lógica interna para el acceso a BD.
 */
public interface IExamenRepo extends IGenericRepo<Examen, Integer>{

}
