package br.com.henrique.service;

import java.io.IOException;
import java.util.List;

import br.com.henrique.dao.ClienteDAO;
import br.com.henrique.model.Cliente;

public class ListaCliente {
	private ClienteDAO clienteDAO;

	public ListaCliente() throws IOException {
		clienteDAO = ClienteDAO.getInstance();
	}

	public List<Cliente> buscarTodos() {
		return clienteDAO.findAll();
	}
}
