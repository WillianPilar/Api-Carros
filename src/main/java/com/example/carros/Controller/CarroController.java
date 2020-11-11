package com.example.carros.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
//	@PostMapping
//	public Carro saveCarro(@RequestBody Carro carro) {
//		return this.carroService.create(carro);
//		
//	}
	
//	@PostMapping
//	public ResponseEntity<?> saveCarro(@RequestBody Carro carro) {
//		
//		try {
//			CarroDTO c = carroService.create(carro); //Manda o CArro para o service para ser criado
//			
//			URI location = getUri(c.getId()); //Executa o método URI mandando o ID do carro que foi criado
//			
//			return ResponseEntity.created(location).build(); // Passa o cod 201 CREATED com a localização de acesso (URL) no HEADER
//		}catch(Exception ex) {
//			return ResponseEntity.badRequest().build(); //Caso de erro retorna um BadREquest
//		} 
//		
//	}
	
	@PostMapping //POST com ExceptionHandler
	public ResponseEntity<?> saveCarro(@RequestBody Carro carro) {
		
		
			CarroDTO c = carroService.create(carro); //Manda o CArro para o service para ser criado
			
			URI location = getUri(c.getId()); //Executa o método URI mandando o ID do carro que foi criado
			
			return ResponseEntity.created(location).build(); // Passa o cod 201 CREATED com a localização de acesso (URL) no HEADER
		
		
	}
	
	private URI getUri(int id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
				.buildAndExpand(id).toUri(); // Monta a URL do carro para colocar no HEADER 
	}
	
//	@PatchMapping(value = "/{id}")
//	public Carro updateCarro(@RequestBody Carro carro, @PathVariable int id ) {
//		return this.carroService.update(id, carro);
//	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateCarro(@RequestBody Carro carro, @PathVariable int id ) {
		
		carro.setId(id);
		
		CarroDTO c = carroService.update(id, carro);
		
		return c != null ? // SE c for != de NULL
				ResponseEntity.ok(c) : // Retorna 200 OK
				ResponseEntity.notFound().build(); //SENÃO retorna 404 NOT FOUND
		
		
	}
	
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<?> delete(@PathVariable int id){
//		boolean ok = carroService.delete(id);
//		
//		return ok ? // Ok == true
//				ResponseEntity.ok().build() : // Retorna 200 OK
//				ResponseEntity.notFound().build(); //SENÃO retorna 404 NOT FOUND
//	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable int id){
			carroService.delete(id);
		
		return ResponseEntity.ok().build(); /// DELETE usando ExceptionHandler
				
	}
	
	
	
	//DIDÁTICO
//	@GetMapping(value = "/fake")
//	public List<Carro> getListCarro(){
//		return carroService.getCarrosFake();
//	}

}
