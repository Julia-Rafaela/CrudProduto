package br.edu.fateczl.CrudProduto.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> {

	public T consultar(T t) throws SQLException, ClassNotFoundException;

	public List<T> listar() throws SQLException, ClassNotFoundException;

}
