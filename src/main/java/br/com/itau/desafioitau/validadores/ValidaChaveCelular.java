/**
 * 
 */
package br.com.itau.desafioitau.validadores;

/**
 * @author Valber Carreiro
 *
 */
public class ValidaChaveCelular {

	public static final String PHONE_MATCH = "^[1-9]{2} \\([1-9]{2,3}\\) [9]{1}[0-9]{4}\\-[0-9]{4}$";
	
	public boolean validacoesCelular(String valorChave) {
		
		boolean iniciaComPlus = valorChave.startsWith("+");
		valorChave = valorChave.replace("+", "");
		boolean atendeRequisitoNumero = valorChave.matches(PHONE_MATCH);
		
		return (iniciaComPlus && atendeRequisitoNumero);
	}
	
}
