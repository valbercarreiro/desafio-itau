/**
 * 
 */
package br.com.itau.desafioitau.validadores;

import br.com.itau.desafioitau.exceptions.ErroValidacaoChaveException;

/**
 * @author Valber Carreiro
 *
 */
public class ValidaChaveCelular {

	public static final String PHONE_MATCH = "^[1-9]{2} [1-9]{2,3} [9]{1}[0-9]{8}$";
	
	public void validacoesCelular(String valorChave) {
		
		boolean iniciaComPlus = valorChave.startsWith("+");
		valorChave = valorChave.replace("+", "");
		boolean atendeRequisitoNumero = valorChave.matches(PHONE_MATCH);

		if(!iniciaComPlus || !atendeRequisitoNumero) {
			throw new ErroValidacaoChaveException("Valor de chave para celular inválida.");
		}
	}
	
}
