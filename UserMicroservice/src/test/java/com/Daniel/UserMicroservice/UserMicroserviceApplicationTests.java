package com.Daniel.UserMicroservice;

import com.Daniel.JPA.UserRepository;
import com.Daniel.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserMicroserviceApplicationTests {

	private UserRepository repositorioTest = mock(UserRepository.class);
	private User userTest = new User(1, "Daniel", "Cabaleiro", "dcabaleiro", "Prueba");
	private User userTest1 = new User(2, "Daniel", "Cabaleiro", "dcabaleiro", "Prueba");

	@Test
	void TestObtenerListDeUsuariosConFindAll() {

		List<User> userList = new ArrayList<>();
		userList.add(userTest);
		userList.add(userTest1);
		when(repositorioTest.findAll()).thenReturn(userList);
		List<User> ListaObtenida = repositorioTest.findAll();
		Assertions.assertTrue(ListaObtenida.get(0).getId() == 1);
	}

	@Test
	void TestObtenerUnUsuarioConFindById(){
		when(repositorioTest.findById(anyInt())).thenReturn(Optional.ofNullable(userTest));
		User usuarioPrueba = repositorioTest.findById(1).get();
		Assertions.assertTrue(usuarioPrueba != null);
	}

}
