package br.com.itau.desafioitau.exceptions;

/**
 * @author Valber Carreiro
 *
 */
public class ErroNegocioException extends RuntimeException {

	private static final long serialVersionUID = 8470591886333701867L;

	public ErroNegocioException(String message) {
		super(message);
	}
	
}
