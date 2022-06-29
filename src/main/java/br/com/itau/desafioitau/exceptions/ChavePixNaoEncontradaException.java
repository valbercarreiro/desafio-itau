package br.com.itau.desafioitau.exceptions;

/**
 * @author Valber Carreiro
 *
 */
public class ChavePixNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 7242996739490076603L;

	public ChavePixNaoEncontradaException(String message) {
		super(message);
	}
	
}
