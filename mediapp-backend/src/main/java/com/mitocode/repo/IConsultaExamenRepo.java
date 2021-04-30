package com.mitocode.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.ConsultaExamen;

public interface IConsultaExamenRepo extends IGenericRepo<ConsultaExamen, Integer>{
	
	/*
	 *  Cuando el query es de tipo DML (Data Manipulation Lenguage) 
	 *  insert-update-delete debe ir la anotación @Modifying and @Transactional
	 */
	@Modifying
	//@Transactional - lo comento porque lo voy a usar en un scoope mayor y abarque más
	@Query(value="INSERT INTO consulta_examen(id_consulta, id_examen) VALUES(:idConsulta, :idExamen)", nativeQuery=true)
	Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);

	
}
