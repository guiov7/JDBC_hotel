package br.com.hotel.src.controller;

import java.sql.Connection;

import br.com.hotel.src.DAO.HospedeDAO;
import br.com.hotel.src.factory.ConnectionFactory;
import br.com.hotel.src.modelo.Hospede;

public class HospedeController extends PatternCtrl<Hospede> {
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().connection();
		super.dao = new HospedeDAO(connection);
	}
}
