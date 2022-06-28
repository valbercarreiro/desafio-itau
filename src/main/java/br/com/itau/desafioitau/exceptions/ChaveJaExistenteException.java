/**
 * 
 */
package br.com.itau.desafioitau.exceptions;

/**
 * @author Valber Carreiro
 *
 */
public class ChaveJaExistenteException extends RuntimeException {

	private static final long serialVersionUID = -3126855724049936588L;

	public ChaveJaExistenteException() {
		super("Chave já existente");
	}
	
	
}
