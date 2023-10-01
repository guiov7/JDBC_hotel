package br.com.hotel.src.controller;

import java.sql.Connection;

import br.com.hotel.src.DAO.ReservaDAO;
import br.com.hotel.src.factory.ConnectionFactory;
import br.com.hotel.src.modelo.Reserva;

public class ReservaController extends PatternCtrl<Reserva> {
	
	public ReservaController() {
		Connection connection = new ConnectionFactory().connection();
		super.dao = new ReservaDAO(connection);
	}
}
