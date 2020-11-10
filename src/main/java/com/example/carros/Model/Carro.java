package com.example.carros.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import javax.persistence.Table;

@Entity(name = "carro")
//@Table(name = "carro")
@Data
public class Carro {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "carro_seq_id")
	@SequenceGenerator (name = "carro_seq_id", sequenceName = "carro_seq_id", allocationSize = 1)
	int id;
	String nome;
	String tipo;
	String descricao;
	
}
