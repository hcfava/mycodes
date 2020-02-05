package br.com.henrique.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.henrique.model.Pedido;
import br.com.henrique.service.XML;

public class PedidoDAO implements XML {
	private static List<Pedido> pedidos = new ArrayList<Pedido>();
	private String fileName = "produtos.xml";
	private static PedidoDAO instance;
	private static final XmlMapper xmlMapper = new XmlMapper();
	private File arquivo = new File(fileName);

	public static synchronized PedidoDAO getInstance() throws IOException {
		if (instance == null) {
			instance = new PedidoDAO();
		}
		return instance;
	}

	public void add(Pedido pedido) throws IOException {
		pedidos.add(pedido);
		escreveListaNoArquivo();
	}

	public List<Pedido> findAll() {
		return Collections.unmodifiableList(leListaNoArquivo());
	}

	public void escreveListaNoArquivo() {
		try {
			xmlMapper.writeValue(arquivo, pedidos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Pedido> leListaNoArquivo() {
		InputStream inputStream = null;
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			inputStream = new FileInputStream(arquivo);
			TypeReference<List<Pedido>> typeReference = new TypeReference<List<Pedido>>() {
			};

			try {
				lista = xmlMapper.readValue(inputStream, typeReference);
			} catch (JsonParseException e) {
				System.out.println("Erro ao fazer parseamento do arquivo");
			} catch (JsonMappingException e) {
				System.out.println("Erro ao fazer mapeamento do arquivo");
			} catch (IOException e) {
				System.out.println("Erro de entrada ou saída.");
			}
			try {
				inputStream.close();
			} catch (IOException e) {
				System.out.println("Erro ao fechar input stream");
			}
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}

		return lista;
	}
}
