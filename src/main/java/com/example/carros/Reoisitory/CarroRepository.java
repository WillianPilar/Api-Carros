package com.example.carros.Reoisitory;

import org.springframework.data.repository.CrudRepository;

import com.example.carros.domain.Carro;

public interface CarroRepository extends CrudRepository<Carro, Integer> {

	Iterable<Carro> findAllByTipo(String tipo);

}
