package com.example.carros;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.carros.Model.Carro;
import com.example.carros.Service.CarroService;

@SpringBootTest
class CarrosApplicationTests {
	
	@Autowired
	private CarroService carroService;
	
	@Test
	public void teste1() {
		Carro carro = new Carro();
		carro.setNome("Teste Spring");
		carro.setTipo("Teste");
		
		carroService.create(carro);
	}

	@Test
	void contextLoads() {
	}

}
