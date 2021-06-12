package com.mitocode.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Menu;

public interface IMenuRepo extends IGenericRepo<Menu, Integer> {
	/**
	 * Como el resultado del query retorna la misma estructura de una entidad
	 * entonces no es necesario que dentro del List<> yo poga un arreglo de Object[]
	 * sino que retorno esa misma entidad -> Menu  
	 */
	@Query(value="select m.* from menu_rol mr inner join usuario_rol ur on ur.id_rol = mr.id_rol inner join menu m on m.id_menu = mr.id_menu inner join usuario u on u.id_usuario = ur.id_usuario where u.nombre = :nombre", nativeQuery = true)
	List<Menu> listarMenuPorUsuario(@Param("nombre") String nombre);

}
