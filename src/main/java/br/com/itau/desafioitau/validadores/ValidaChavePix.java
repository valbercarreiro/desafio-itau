/**
 * 
 */
package br.com.itau.desafioitau.validadores;

import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.service.ChavePixService;

/**
 * @author Valber Carreiro
 *
 */
public abstract class ValidaChavePix {
	
	private ChavePixService chavePixService;
	
	public ValidaChavePix(ChavePixService chavePixService) {
		this.chavePixService = chavePixService;
	}

	abstract boolean validarChaveCriacao(ChavePix chavePix);

	public boolean validaChavePix(ChavePix chavePix) {
		
		Integer countByValorChave = this.chavePixService.countByValorChave(chavePix.getValorChave());
		if(countByValorChave > 0) {
			return false;
		}
		
		this.validarChaveCriacao(chavePix);
		
		return true;
	}
	
}
