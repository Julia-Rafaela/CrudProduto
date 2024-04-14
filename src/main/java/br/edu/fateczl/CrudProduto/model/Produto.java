package br.edu.fateczl.CrudProduto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Produto {

	int codigo;
	String nome;
	Float valorUnitario;
	int quantidade;

	public String toString() {
		return nome;
	}
}