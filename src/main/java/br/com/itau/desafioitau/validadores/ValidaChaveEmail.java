/**
 * 
 */
package br.com.itau.desafioitau.validadores;

import br.com.itau.desafioitau.exceptions.ErroValidacaoChaveException;

/**
 * @author Valber Carreiro
 *
 */
public class ValidaChaveEmail {

	public static final String EMAIL_MATCH = "[A-Za-z0-9+_.-]+@(.+)$";
	
	public void validacoesEmail(String valorChave) {
		
		boolean tamanhoOK = valorChave.length() <= 77;
		boolean emailOK = valorChave.matches(EMAIL_MATCH);
		
		if(!tamanhoOK || !emailOK) {
			throw new ErroValidacaoChaveException("Valor de chave e-mail inválida.");
		}
	}

}
