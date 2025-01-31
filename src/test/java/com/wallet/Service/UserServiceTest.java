package com.wallet.Service;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.Entity.User;
import com.wallet.Repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	//Mockando o Repository
	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;
	
	//O que será feito antes dos tests
	@Before
	public void setup() {
		//Passando qualquer string ao ser chamando o método em Service e Mockar o resultado
		BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new User()));
	}
	
	@Test
	public void testFindByEmail() {
		Optional<User> user = service.findByEmail("Teste@Teste.com.br");
		
		assertTrue(user.isPresent());
	}
}
