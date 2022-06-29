package br.com.itau.desafioitau.validadores;

import br.com.itau.desafioitau.exceptions.ErroValidacaoChaveException;

/**
 * @author Valber Carreiro
 *
 */
public class ValidaChaveAleatoria {
	
	public void validacoesChaveAleatoria(String valorChave) {
		
		if(!(valorChave.length() <= 36)) {
			throw new ErroValidacaoChaveException("Valor de chave aleatória inválida.");
		}
		
	}

}
