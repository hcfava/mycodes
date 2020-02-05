package br.com.henrique.model;

public class Cliente {
	private int id;
	private String nome;
	private String endereco;

	public Cliente(int id, String nome, String endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}
	
	@Override
	public String toString() {
		return "ID: " + this.getId() + "\nNome: " + this.getNome() + "\nEndereço: " + this.getEndereco();
	}

}
