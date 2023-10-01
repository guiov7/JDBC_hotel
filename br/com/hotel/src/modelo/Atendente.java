package br.com.hotel.src.modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Atendente extends Usuario implements Autenticavel {
	private String user;
	private String password;

	public Atendente(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public Atendente(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone) {
		super(nome, sobrenome, dataNascimento, nacionalidade, telefone);
	}

	@Override
	public String getUser() {
		return this.user;
	}

	@Override
	public String getSenha() {
		return this.password;
	}

	@Override
	public boolean autenticar(PreparedStatement stm) {
		try (ResultSet rst = stm.getResultSet()) {
			return rst.next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}