package com.example.carros.Reoisitory;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.carros.Model.Carro;

public interface CarroRepository extends CrudRepository<Carro, Integer> {

	List<Carro> findAllByTipo(String tipo);

}
