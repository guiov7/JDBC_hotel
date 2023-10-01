package br.com.hotel.src.modelo;

import java.sql.PreparedStatement;

public interface Autenticavel {

	String getUser();

	String getSenha();

	boolean autenticar(PreparedStatement stm);
}
