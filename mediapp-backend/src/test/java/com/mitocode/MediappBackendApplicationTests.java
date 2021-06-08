package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;

@SpringBootTest
class MediappBackendApplicationTests {

	@Autowired
	private IUsuarioRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt; // Permite hacer un hash de texto
	
	@Test
	void verficarClave() {
		Usuario user = new Usuario();
		user.setIdUsuario(1);
		user.setUsername("andres@gmail.com");
		user.setPassword(bcrypt.encode("123"));
		user.setEnabled(true);
		
		Usuario retorno = repo.save(user);
		assertTrue(retorno.getPassword().equals(user.getPassword()));
	}

}
