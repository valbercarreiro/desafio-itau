package br.com.itau.desafioitau.validadores;

/**
 * @author Valber Carreiro
 *
 */
public class ValidaChaveAleatoria {
	
	public boolean validacoesChaveAleatoria(String valorChave) {
		
		return valorChave.length() <= 36;
		
	}

}
