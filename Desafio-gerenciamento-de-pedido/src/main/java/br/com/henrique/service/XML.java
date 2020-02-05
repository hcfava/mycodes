package br.com.henrique.service;
import java.util.List;

public interface XML {

	public abstract void escreveListaNoArquivo();
	
	public abstract List<?> leListaNoArquivo();
}
