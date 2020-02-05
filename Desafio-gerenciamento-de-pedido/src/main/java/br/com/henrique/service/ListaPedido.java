package br.com.henrique.service;

import java.io.IOException;
import java.util.List;

import br.com.henrique.dao.PedidoDAO;
import br.com.henrique.model.Pedido;

public class ListaPedido {
	private PedidoDAO pedidoDAO;

	public ListaPedido() throws IOException {
		pedidoDAO = PedidoDAO.getInstance();
	}

	public List<Pedido> buscarTodos() {
		return pedidoDAO.findAll();
	}
}
