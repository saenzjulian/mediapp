package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mitocode.model.Paciente;

/*
 * Esta interfaz tiene acceso directo a la base de datos
 * porque tiene herencias de JpaRepository y esta ya tiene toda
 * la lógica interna para el acceso a BD.
 */
public interface IPacienteRepo extends JpaRepository<Paciente, Integer>{

}
