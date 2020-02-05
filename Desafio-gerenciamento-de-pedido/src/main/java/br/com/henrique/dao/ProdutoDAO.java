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

import br.com.henrique.model.Produto;
import br.com.henrique.service.XML;

public class ProdutoDAO implements XML {
	private static List<Produto> produtos = new ArrayList<Produto>();
	private String fileName = "produtos.xml";
	private static ProdutoDAO instance;
	private static final XmlMapper xmlMapper = new XmlMapper();
	private File arquivo = new File(fileName);

	public static synchronized ProdutoDAO getInstance() throws IOException {
		if (instance == null) {
			instance = new ProdutoDAO();
		}
		return instance;
	}

	public void add(Produto produto) throws IOException {
		produtos.add(produto);
		escreveListaNoArquivo();
	}

	public List<Produto> findAll() {
		return Collections.unmodifiableList(leListaNoArquivo());
	}

	public List<Produto> findAllEstoque() {
		List<Produto> estoque = new ArrayList<Produto>(findAll());
		produtos.forEach(p -> {
			if (!p.temEmEstoque())
				estoque.remove(p);
		});
		return Collections.unmodifiableList(estoque);
	}

	@Override
	public void escreveListaNoArquivo() {
		try {
			xmlMapper.writeValue(arquivo, produtos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Produto> leListaNoArquivo() {
		InputStream inputStream = null;
		List<Produto> lista = new ArrayList<Produto>();
		try {
			inputStream = new FileInputStream(arquivo);
			TypeReference<List<Produto>> typeReference = new TypeReference<List<Produto>>() {
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
			System.out.println("Erro, arquivo não encontrado!");
		}
		
		return lista;
	}
}
