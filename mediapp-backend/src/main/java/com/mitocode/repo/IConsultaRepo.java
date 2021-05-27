package com.mitocode.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Consulta;

/*
 * Esta interfaz tiene acceso directo a la base de datos
 * porque tiene herencias de JpaRepository y esta ya tiene toda
 * la lógica interna para el acceso a BD.
 */
public interface IConsultaRepo extends IGenericRepo<Consulta, Integer>{
	/**
	 * Desde el componente buscar aquí voy a realizar dos querys
	 * uno para buscar por nombre o apellido
	 * y el otro buscar por fecha.
	 */
	@Query("FROM Consulta c "
			+ "WHERE c.paciente.dni =:dni "
			+ "OR LOWER(c.paciente.nombres) LIKE %:nombreCompleto% "
			+ "OR LOWER(c.paciente.apellidos) LIKE %:nombreCompleto%")
	List<Consulta> buscar(@Param("dni") String dni, @Param("nombreCompleto") String nombreCompleto);
	
	@Query("FROM Consulta c "
			+ "WHERE c.fecha BETWEEN :fechaConsulta AND :fechaSgte")
	List<Consulta> buscarFecha(@Param("fechaConsulta") LocalDateTime fechaConsulta, @Param("fechaSgte") LocalDateTime fechaSgte);

}
