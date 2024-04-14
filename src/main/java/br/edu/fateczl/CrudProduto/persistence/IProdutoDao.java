package br.edu.fateczl.CrudProduto.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.CrudProduto.model.Produto;


public interface IProdutoDao {
	
	public String iudProduto (String acao, Produto p) throws SQLException, ClassNotFoundException;
	public List<Produto> listarProduto() throws SQLException, ClassNotFoundException;

}
