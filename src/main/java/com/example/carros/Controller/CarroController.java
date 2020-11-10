package com.example.carros.Controller;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.DTO.CarroDTO;
import com.example.carros.Model.Carro;
import com.example.carros.Service.CarroService;



@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<CarroDTO>> getCarro(){
		return new ResponseEntity<>(carroService.getAllCarros(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Carro> getCarroById(@PathVariable("id") int id){
		Optional<Carro> carro = carroService.getCarroById(id);
		return carro.map(ResponseEntity :: ok) // Se tiver carro, retorna 200 OK
				.orElse(ResponseEntity.notFound().build()); // Se não, retorna 404
	}
	
	@GetMapping(value = "/tipo/{tipo}")
	public ResponseEntity <List<CarroDTO>>getCarroByTipo(@PathVariable("tipo") String tipo){
		List<CarroDTO> carro = carroService.getCarroByTipo(tipo);
		
		return carro.isEmpty() ? // SE a lista de carro estiver vazia ...
				ResponseEntity.noContent().build() : // Retorne 204 NoContent
					ResponseEntity.ok(carro); // Senão retorne a lista de carros
	}
	
	@PostMapping
	public Carro saveCarro(@RequestBody Carro carro) {
		return this.carroService.create(carro);
		
	}
	
	@PatchMapping(value = "/{id}")
	public Carro updateCarro(@RequestBody Carro carro, @PathVariable int id ) {
		return this.carroService.update(id, carro);
	}
	
	@DeleteMapping(value = "/{id}")
	public String delete(@PathVariable int id){
		return carroService.delete(id);
	}
	
	
	
	//DIDÁTICO
//	@GetMapping(value = "/fake")
//	public List<Carro> getListCarro(){
//		return carroService.getCarrosFake();
//	}

}
