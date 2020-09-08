package br.com.softblue.bluefood.application.service;

@SuppressWarnings("serial")
public class PagamentoExeption extends Exception {

	public PagamentoExeption() {
	}

	public PagamentoExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public PagamentoExeption(String message) {
		super(message);
	}

	public PagamentoExeption(Throwable cause) {
		super(cause);
	}

}
