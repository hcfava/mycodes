package br.com.henrique.service;

import java.io.IOException;

import br.com.henrique.dao.PedidoDAO;
import br.com.henrique.model.Pedido;

public class CadastraPedido {
	private PedidoDAO pedidoDAO;

	public CadastraPedido() throws IOException {
		pedidoDAO = PedidoDAO.getInstance();
	}

	public void cadastrarPedido(Pedido pedido) throws IOException {
		pedidoDAO.add(pedido);
	}
}
