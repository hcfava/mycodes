package br.com.henrique.service;

import java.io.IOException;

import br.com.henrique.dao.ClienteDAO;
import br.com.henrique.model.Cliente;

public class CadastraCliente {
	private ClienteDAO clienteDAO;

	public CadastraCliente() throws IOException {
		clienteDAO = ClienteDAO.getInstance();
	}

	public void cadastrarCliente(Cliente cliente) throws IOException {
		clienteDAO.add(cliente);
	}
}
