package br.com.henrique.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private int id;
    private int clienteId;
    private String data;
    private List<Produto> produtosDoPedido = new ArrayList<Produto>();
    private double valorTotal = 0;

            
    public Pedido(int id, int clienteId, String data) {
		this.id = id;
		this.clienteId = clienteId;
		this.data = data;
	}

	public int getId() {
        return id;
    }


    public int getClienteId() {
        return clienteId;
    }

    public String getData() {
        return data;
    }
    
    public double getValorTotal() {
		return valorTotal;
	}
    
    public boolean adiocionaProduto(Produto produto, int quantidade) {
    	if(produto.temEmEstoque() && produto.getQuantidade() >= quantidade) {
    		produto.setQuantidade(quantidade);
    		valorTotal += quantidade* produto.getPreco();
    		return produtosDoPedido.add(produto);
    	}
    	else
    		return false;
    }
    
    public List<Produto> getProdutosDoPedido() {
		return produtosDoPedido;
	}
    
}
