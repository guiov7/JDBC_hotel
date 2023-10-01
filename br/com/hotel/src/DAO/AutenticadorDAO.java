package br.com.hotel.src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.hotel.src.modelo.Autenticavel;

public class AutenticadorDAO {
    private Connection connection;

	public AutenticadorDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean login(Autenticavel autenticavel) {
		String sql = "SELECT * FROM usuario WHERE user = ? AND senha = ?";
		try (PreparedStatement stm = this.connection.prepareStatement(sql)) {
			stm.setString(1, autenticavel.getUser());
			stm.setString(2, autenticavel.getSenha());
			stm.execute();

			return autenticavel.autenticar(stm);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
