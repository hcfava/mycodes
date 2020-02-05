package br.com.henrique.model;

public class Produto {
	private int codigo;
	private String descricao;
	private double preco;
	private int quantidade;

	public Produto(int codigo, String descricao, double preco, int quantidade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	public Produto(Produto produto) {
		this.codigo = produto.getCodigo();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.quantidade = produto.getQuantidade();
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public boolean temEmEstoque() {
		if(quantidade > 0)
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "Codigo: " + this.getCodigo() + "\nProduto: " + this.getDescricao() + "\nPreço: " + this.getPreco() + "\nQuantidade: "
				+ this.getQuantidade();
	}

}
