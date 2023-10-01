package br.com.hotel.src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.hotel.src.modelo.Hospede;

public class HospedeDAO implements DAO<Hospede> {

	private Connection connection;

	public HospedeDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Hospede> listar() {
		List<Hospede> hospedes = new ArrayList<>();
		String sql = "SELECT * FROM hospede";
		try (PreparedStatement stm = this.connection.prepareStatement(sql)) {
			stm.execute();
			return converteResult(hospedes, stm);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void salvar(Hospede hospede) {
		String sql = "INSERT INTO hospede(nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stm.setString(1, hospede.getNome());
			stm.setString(2, hospede.getSobrenome());
			stm.setString(3, hospede.getDataNascimento().toString());
			stm.setString(4, hospede.getNacionalidade());
			stm.setString(5, hospede.getTelefone());
			stm.setInt(6, hospede.getIdReserva());
			stm.execute();
			try (ResultSet rst = stm.getGeneratedKeys()) {
				while (rst.next()) {
					hospede.setId(rst.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Hospede> buscar(String busca) {
		try {
			String sql = "SELECT * FROM hospede WHERE id_reserva = ? OR sobrenome = ?";
			List<Hospede> hospedes = new ArrayList<>();
			try (PreparedStatement stm = this.connection.prepareStatement(sql)) {
				stm.setString(1, busca);
				stm.setString(2, busca);
				stm.execute();
				converteResult(hospedes, stm);
			}
			return hospedes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deletar(Integer id) {
		try {
			String sql = "DELETE FROM hospede WHERE id = ?";
			try (PreparedStatement stm = this.connection.prepareStatement(sql)) {
				stm.setInt(1, id);
				stm.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void alterar(Hospede hospede) {
		try {
			String sql = "UPDATE hospede SET nome = ?, sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ? WHERE id = ?";
			try (PreparedStatement stm = this.connection.prepareStatement(sql)) {
				stm.setString(1, hospede.getNome());
				stm.setString(2, hospede.getSobrenome());
				stm.setString(3, hospede.getDataNascimento().toString());
				stm.setString(4, hospede.getNacionalidade());
				stm.setString(5, hospede.getTelefone());
				stm.setInt(6, hospede.getId());
				stm.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Hospede> converteResult(List<Hospede> hospedesList, PreparedStatement stm) {
		try (ResultSet rst = stm.getResultSet()) {
			while (rst.next()) {
				Hospede hospede = new Hospede(
						rst.getInt(1), rst.getString(2), rst.getString(3),
						LocalDate.parse(rst.getString(4)), rst.getString(5),
						rst.getString(6), rst.getInt(7));
				hospedesList.add(hospede);
			}
			return hospedesList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}