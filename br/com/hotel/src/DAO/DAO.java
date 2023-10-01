package br.com.hotel.src.DAO;

import java.util.List;
// gestor Data Object interface
public interface DAO<T> {

	List<T> listar();

	void salvar(T t);

	List<T> buscar(String busca);

	void deletar(Integer id);

	void alterar(T t);
}