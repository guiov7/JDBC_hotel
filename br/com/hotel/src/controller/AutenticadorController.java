package br.com.hotel.src.controller;

import java.sql.Connection;

import br.com.hotel.src.DAO.AutenticadorDAO;
import br.com.hotel.src.factory.ConnectionFactory;
import br.com.hotel.src.modelo.Autenticavel;



public class AutenticadorController {

	private AutenticadorDAO autenticacaoDAO;
	private Autenticavel autenticavel;

	public AutenticadorController(Autenticavel autenticavel) {
		Connection connection = new ConnectionFactory().connection();

		this.autenticacaoDAO = new AutenticadorDAO(connection);
		this.autenticavel = autenticavel;
	}

	public boolean login() {
		return this.autenticacaoDAO.login(this.autenticavel);
	}
}

