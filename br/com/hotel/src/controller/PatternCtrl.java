package br.com.hotel.src.controller;

import java.util.List;

import br.com.hotel.src.DAO.DAOgestor;

public class PatternCtrl<T> {
	
	public DAOgestor<T> dao;
	
	public void salvar(T t) {
		this.dao.salvar(t);
	}

	public List<T> listar() {
		return this.dao.listar();
	}

	public void deletar(Integer id) {
		this.dao.deletar(id);
	}

	public void alterar(T t) {
		this.dao.alterar(t);
	}

	public List<T> pesquisar(String pesquisa) {
		return this.dao.buscar(pesquisa);
	}
}
