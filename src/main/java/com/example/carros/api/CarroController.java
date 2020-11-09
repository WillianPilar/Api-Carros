package com.example.carros.api;

//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.Service.CarroService;
import com.example.carros.domain.Carro;



@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public Iterable<Carro> getCarro(){
		return carroService.getAllCarros();
	}
	
	@GetMapping(value = "/{id}")
	public Optional<Carro> getCarroById(@PathVariable("id") int id){
		return carroService.getCarroById(id);
	}
	
	@GetMapping(value = "/tipo/{tipo}")
	public Iterable<Carro> getCarroByTipo(@PathVariable("tipo") String tipo){
		return carroService.getCarroByTipo(tipo);
	}
	
	@PostMapping
	public Carro saveCarro(@RequestBody Carro carro) {
		return this.carroService.create(carro);
		
	}
	
	@PatchMapping(value = "/{id}")
	public Carro updateCarro(@RequestBody Carro carro, @PathVariable int id ) {
		return this.carroService.update(id, carro);
	}
	
	
	
	//DIDÁTICO
//	@GetMapping(value = "/fake")
//	public List<Carro> getListCarro(){
//		return carroService.getCarrosFake();
//	}

}
