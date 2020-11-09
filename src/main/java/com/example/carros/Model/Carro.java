package com.example.carros.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;

@Entity(name = "carro")
//@Table(name = "carro")
public class Carro {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "carro_seq_id")
	@SequenceGenerator (name = "carro_seq_id", sequenceName = "carro_seq_id", allocationSize = 1)
	int id;
	String nome;
	String tipo;
	
	

	public Carro(int id, String nome, String tipo){
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public Carro(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
