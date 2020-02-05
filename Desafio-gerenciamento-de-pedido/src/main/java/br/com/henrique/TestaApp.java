package br.com.henrique;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.henrique.model.Cliente;
import br.com.henrique.model.Pedido;
import br.com.henrique.model.Produto;
import br.com.henrique.service.CadastraCliente;
import br.com.henrique.service.CadastraPedido;
import br.com.henrique.service.CadastraProduto;
import br.com.henrique.service.ListaCliente;
import br.com.henrique.service.ListaPedido;
import br.com.henrique.service.ListaProduto;

public class TestaApp {
	private static Scanner sc = new Scanner(System.in);
	private static CadastraCliente cadastraCliente;
	private static CadastraProduto cadastraProduto;
	private static ListaProduto listaProduto;
	private static ListaCliente listaCliente;
	private static CadastraPedido cadastraPedido;
	private static ListaPedido listaPedido;

	public static void main(String[] args) throws IOException {
		int opcao = 0;
		cadastraCliente = new CadastraCliente();
		cadastraProduto = new CadastraProduto();
		listaProduto = new ListaProduto();
		cadastraPedido = new CadastraPedido();
		listaCliente = new ListaCliente();
		listaPedido = new ListaPedido();
		do {
			System.out.println("## Escolha uma das op��es abaixo ##");
			System.out.println("Op��o 1 - Verificar Estoque");
			System.out.println("Op��o 2 - Realizar Pedido");
			System.out.println("Op��o 3 - Cadastrar Produto");
			System.out.println("Op��o 4 - Cadastrar Cliente");
			System.out.println("Op��o 5 - Sair do programa");
			System.out.println("_______________________");

			System.out.print("Digite aqui sua op��o: ");
			opcao = menu(sc.nextInt());
		} while (opcao != 5);

		sc.close();
	}

	private static int menu(int opcao) throws IOException {
		switch (opcao) {
		case 1:

			List<Produto> listProdutoTMP = listaProduto.buscarTodosEstoque();
			if (listProdutoTMP.isEmpty()) {
				System.out.println("\nN�o existem produtos em estoque\n");
			} else {
				System.out.println(listProdutoTMP.toString());
			}

			break;
		case 2:

			List<Produto> listProdutoPedido = listaProduto.buscarTodosEstoque();
			List<Cliente> listaClientes = listaCliente.buscarTodos();
			List<Pedido> listaPedidos = listaPedido.buscarTodos();
			int idPedido;
			int idCliente;
			boolean achou = false;

			System.out.print("Digite o c�digo do pedido: ");
			idPedido = sc.nextInt();
			for (Pedido pedido : listaPedidos) {
				if(pedido.getId() == idPedido) {
					System.out.println("J� existe um pedido com esse ID");
					break;
				}
			}

			System.out.print("Digite o id do cliente: ");
			idCliente = sc.nextInt();
			for (int i = 0; i < listaClientes.size(); i++) {
				if (listaClientes.get(i).getId() == idCliente) {
					achou = true;
					break;
				}
			}
			if (achou == false) {
				System.out.println();
				System.out.println("Cliente n�o encontrado");
				System.out.println();
				break;
			}

			DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date data = new Date();
			Pedido pedido = new Pedido(idPedido, idCliente, format.format(data));
			char finalizado = 'n';
			do {
				achou = false;
				int idProduto;
				System.out.print("Digite o id do produto: ");
				idProduto = sc.nextInt();
				for (int i = 0; i < listProdutoPedido.size(); i++) {
					if (listProdutoPedido.get(i).getCodigo() == idProduto) {
						achou = true;
						Produto ProdutoPedido = new Produto(listProdutoPedido.get(i));
						int quantidadeProduto;
						System.out.print("Digite a quantidade a ser adicionada no pedido: ");
						quantidadeProduto = sc.nextInt();
						pedido.adiocionaProduto(ProdutoPedido, quantidadeProduto);
						break;
					}
				}
				if (achou == false) {
					System.out.println("Produto n�o encontrado!");
					System.out.println();
					continue;
				}
				System.out.println("Deseja finalizar o ao pedido?");
				System.out.print("Digite 'n' para adicionar mais algum produto ou 's' para terminar o pedido: ");
				sc = new Scanner(System.in);
				finalizado = sc.nextLine().charAt(0);
				if (finalizado == 's') {
					cadastraPedido.cadastrarPedido(pedido);
					break;
				}

			} while (finalizado != 's');

			break;

		case 3:

			int codigo;
			String descricao;
			double preco;
			int quantidade;

			System.out.print("Digite o c�digo do produto: ");
			codigo = sc.nextInt();

			System.out.print("Digite a descri��o do produto: ");
			sc = new Scanner(System.in);
			descricao = sc.nextLine();

			System.out.print("Digite o pre�o do produto: ");
			preco = sc.nextDouble();

			System.out.print("Digite a quantidade em estoque: ");
			quantidade = sc.nextInt();

			System.out.println();

			Produto produto = new Produto(codigo, descricao, preco, quantidade);
			// Guarda o objeto produto em uma lista.
			cadastraProduto.cadastrarProduto(produto);

			break;

		case 4:

			int id;
			String nome;
			String endereco;
			List<Cliente> listaClientesTMP = listaCliente.buscarTodos();
			
			System.out.print("Digite o id do cliente: ");
			id = sc.nextInt();
			for (Cliente cliente : listaClientesTMP) {
				if(id == cliente.getId()) {
					System.out.println("J� existe um cliente com esse ID");
					break;
				}
			}
			

			System.out.print("Digite o nome do cliente: ");
			sc = new Scanner(System.in);
			nome = sc.nextLine();

			System.out.print("Digite o endere�o do cliente: ");
			sc = new Scanner(System.in);
			endereco = sc.nextLine();

			System.out.println();

			Cliente cliente = new Cliente(id, nome, endereco);
			// Guarda o objeto cliente em uma lista.
			cadastraCliente.cadastrarCliente(cliente);

			break;

		case 5:
			break;
		default:
			System.out.println("Op��o inv�lida!");
			break;
		}
		return opcao;
	}
}
