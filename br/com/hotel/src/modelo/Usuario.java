package br.com.hotel.src.modelo;

import java.time.LocalDate;
import java.time.Period;

public abstract class Usuario {

	protected String nome;
	protected String sobrenome;
	protected LocalDate dataNascimento;
	protected String nacionalidade;
	protected String telefone;

	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, LocalDate dataNascimento, String nacionalidade, String telefone) {
		if (nome.isEmpty() || sobrenome.isEmpty() || dataNascimento == null || nacionalidade.isEmpty() || telefone.isEmpty()) {
			throw new IllegalArgumentException("Preencha todos os campos");
		}
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.nacionalidade = nacionalidade;
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return this.nome + " " + this.sobrenome + ", " + this.dataNascimento + ", " + this.getIdade()
				+ " anos de idade, " + this.nacionalidade;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public int getIdade() {
		return Period.between(this.dataNascimento, LocalDate.now()).getYears();
	}

	public String getNacionalidade() {
		return this.nacionalidade;
	}

	public String getTelefone() {
		return this.telefone;
	}

}
