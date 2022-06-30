/**
 * 
 */
package br.com.itau.desafioitau.validadores;

import br.com.itau.desafioitau.exceptions.ErroNegocioException;
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

	abstract void validarChaveCriacao(ChavePix chavePix);

	public void validaChavePix(ChavePix chavePix) {
		
		validaNomeSobrenome(chavePix);
		Integer countByValorChave = this.chavePixService.countByValorChave(chavePix.getValorChave());
		if(countByValorChave > 0) {
			throw new ErroNegocioException("Chave já cadastrada!");
		}
		
		this.validarChaveCriacao(chavePix);
		
	}
	
	public void validaNomeSobrenome(ChavePix chavePix) {
		if(chavePix.getNomeCorrentista().length() > 30) {
			throw new ErroNegocioException("Campo Nome Correntista não pode ultrapassar 30 caracteres!");
		}
		
		if(chavePix.getSobrenomeCorrentista() != null && chavePix.getSobrenomeCorrentista().length() > 45) {
			throw new ErroNegocioException("Campo Nome Correntista não pode ultrapassar 45 caracteres!");
		}
	}
	
}
