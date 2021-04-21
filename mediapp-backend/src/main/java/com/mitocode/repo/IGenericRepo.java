package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author jags
 *
 * @param <T> Tipo
 * @param <ID> Valor
 * 
 * La anotación @NoRepositoryBean es para que Spring tome la interfaz
 * como de configuración porque es la que le va a heredar a las otras
 * para no repetir código
 */
@NoRepositoryBean
public interface IGenericRepo<T, ID> extends JpaRepository<T, ID>{

}
