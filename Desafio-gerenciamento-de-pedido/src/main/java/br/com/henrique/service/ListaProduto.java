package br.com.henrique.service;

import java.io.IOException;
import java.util.List;

import br.com.henrique.dao.ProdutoDAO;
import br.com.henrique.model.Produto;

public class ListaProduto {
	private ProdutoDAO produtoDAO;

	public ListaProduto() throws IOException {
		produtoDAO = ProdutoDAO.getInstance();
	}

	public List<Produto> buscarTodos() {
		return produtoDAO.findAll();
	}

	public List<Produto> buscarTodosEstoque() {
		return produtoDAO.findAllEstoque();
	}
}
