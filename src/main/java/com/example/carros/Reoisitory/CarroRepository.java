package com.example.carros.Reoisitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.example.carros.Model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer> {

	List<Carro> findAllByTipo(String tipo);

}
