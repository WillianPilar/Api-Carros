package com.example.carros.DTO;

import org.modelmapper.ModelMapper;

import com.example.carros.Model.Carro;

import lombok.Data;

@Data
public class CarroDTO {
	
	private int id;
	private String nome;
	private String tipo;
	
	public static CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();// Usado para criar os construtores DTO de forma automática 
		return modelMapper.map(c, CarroDTO.class); //Transforma o carro que vir em DTO
	}
	

}
