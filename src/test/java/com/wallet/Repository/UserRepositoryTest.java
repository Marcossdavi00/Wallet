package com.wallet.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.Entity.User;

//Configuração dos Testes TDD
@RunWith(SpringRunner.class)
@SpringBootTest

public class UserRepositoryTest {

	private static final String EMAIL = "Teste@Teste.com.br";
	
	@Autowired
	UserRepository repository;
	
	@Before
	public void setup() {
		User u = new User();
		u.setName("Marcos");
		u.setPassword("Marcos123");
		u.setEmail(EMAIL);
		
		repository.save(u);
	}
	@After
	public void tearDown() {
		
		repository.deleteAll();
	}
	
	//Teste do método salvar
	@Test
	public void testSave() {
		User u = new User();
		
		u.setName("Test");
		u.setPassword("123456");
		u.setEmail("Teste@Teste.com.br");
		
		User response = repository.save(u);
		
		assertNotNull(response);
	}
	//Teste de busca por email
	@Test
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmailEquals(EMAIL);
		
		assertTrue(response.isPresent());
		assertEquals(response.get().getEmail(), EMAIL);
	}
}
