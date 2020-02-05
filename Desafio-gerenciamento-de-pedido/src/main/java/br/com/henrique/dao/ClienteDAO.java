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
//import org.codehaus.jackson.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.com.henrique.model.Cliente;
import br.com.henrique.service.XML;

public class ClienteDAO implements XML {
	private static List<Cliente> clientes = new ArrayList<Cliente>();
	private static ClienteDAO instance;
	private String fileName = "clientes.xml";
	private File arquivo = new File(fileName);
	private XmlMapper xmlMapper = new XmlMapper();

//	private ClienteDAO() throws IOException {
//		this.lerArquivo.readFile(fileName);
//	}

	public static synchronized ClienteDAO getInstance() throws IOException {
		if (instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}

	public void add(Cliente cliente) throws IOException {
		
		clientes.add(cliente);
		escreveListaNoArquivo();
	}

	public List<Cliente> findAll() {
		return Collections.unmodifiableList(leListaNoArquivo());
	}

	public void escreveListaNoArquivo() {
		try {
			xmlMapper.writeValue(arquivo, clientes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Cliente> leListaNoArquivo() {
		InputStream inputStream = null;
		//List<Cliente> lista = new ArrayList<Cliente>();
		try {
			inputStream = new FileInputStream(arquivo);
			TypeReference<List<Cliente>> typeReference = new TypeReference<List<Cliente>>() {};
			
			try {
				clientes = xmlMapper.readValue(inputStream, typeReference);
				
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
		return clientes;

	}

}
