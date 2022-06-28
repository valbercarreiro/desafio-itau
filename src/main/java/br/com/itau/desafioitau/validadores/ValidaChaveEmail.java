/**
 * 
 */
package br.com.itau.desafioitau.validadores;

/**
 * @author Valber Carreiro
 *
 */
public class ValidaChaveEmail {

	public static final String EMAIL_MATCH = "[A-Za-z0-9+_.-]+@(.+)$";
	
	public boolean validacoesEmail(String valorChave) {
		
		boolean tamanhoOK = valorChave.length() <= 77;
		boolean emailOK = valorChave.matches(EMAIL_MATCH);
		
		return (tamanhoOK && emailOK);
	}

}
