package br.com.itau.desafioitau.exceptions;

public class ErroValidacaoChaveException extends RuntimeException {

	private static final long serialVersionUID = 2671652730038803604L;
	
	public ErroValidacaoChaveException(String message) {
		super(message);
	}

}
