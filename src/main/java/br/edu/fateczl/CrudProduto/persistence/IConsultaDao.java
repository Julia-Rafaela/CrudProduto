package br.edu.fateczl.CrudProduto.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.CrudProduto.model.Produto;


public interface IConsultaDao {
	
	public List<Produto> listarProduto(int valor) throws SQLException, ClassNotFoundException;
	public int calcularQtd(int QtdProdutos) throws SQLException, ClassNotFoundException;

}
