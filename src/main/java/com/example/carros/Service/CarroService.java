package com.example.carros.Service;

import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carros.DTO.CarroDTO;
import com.example.carros.Model.Carro;
import com.example.carros.Reoisitory.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository carroRepository;
	
	public List<CarroDTO> getAllCarros(){
		 
		return this.carroRepository.findAll().stream().map(CarroDTO :: create).collect(Collectors.toList());
		//findAll() -- Retorna Lista de carros
		//.stream() -- Chama para mapear a lista com o .map() que percorre todo o CarroDTO e cria NEW carros
		//.colect(Collectors.toList()); -- Gera uma nova lista de CarrosDTO
	}
	
	public Optional<Carro> getCarroById(int id){
		return this.carroRepository.findById(id);
		
		//return this.carroRepository.findById(id).map(CarroDTO :: new);
		//Retorna do DTO se existir, caso contrário manda um optional
	}
	
	public List<CarroDTO> getCarroByTipo(String tipo){
		return this.carroRepository.findAllByTipo(tipo).stream().map(CarroDTO :: create).collect(Collectors.toList());
	}

	public Carro create(Carro carro) {
		
		return carroRepository.save(carro);
		
	}

	public Carro update(int id, Carro carro) {
		
		Optional<Carro> optionalCarro = this.carroRepository.findById(id);
		Carro update = null;
		
		if(optionalCarro.isPresent()) {
			
			update = optionalCarro.get();
			
			update.setNome(carro.getNome());
			update.setTipo(carro.getTipo());
			
			
			update = this.carroRepository.save(update);
		}
		
		return update;
	}

	public String delete(int id) {
		Optional<Carro> carro = carroRepository.findById(id);
		if(carro.isPresent()) {
			carroRepository.deleteById(id);
		}else {
			return "Carro não existe!";
		}
		return "Carro de ID " + id + " deletado com sucesso!";
	}
		

	
	
	//DIDÁTICO
//	public List<Carro> getCarrosFake(){
//		List<Carro> carros = new ArrayList<>();
//		
//		carros.add(new Carro(1 , "Fusca"));
//		carros.add(new Carro(2 , "Celta"));
//		carros.add(new Carro(3 , "Siena"));
//			
//		return carros;
//	}

}