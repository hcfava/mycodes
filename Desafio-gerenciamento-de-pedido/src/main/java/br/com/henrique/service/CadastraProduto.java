package br.com.henrique.service;

import java.io.IOException;

import br.com.henrique.dao.ProdutoDAO;
import br.com.henrique.model.Produto;

public class CadastraProduto {
	private ProdutoDAO produtoDAO;

	public CadastraProduto() throws IOException {
		produtoDAO = ProdutoDAO.getInstance();
	}

	public void cadastrarProduto(Produto produto) throws IOException {
		produtoDAO.add(produto);
	}
}
