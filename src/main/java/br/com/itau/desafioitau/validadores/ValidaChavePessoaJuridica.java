/**
 * 
 */
package br.com.itau.desafioitau.validadores;

import org.springframework.stereotype.Component;

import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.service.ChavePixService;

/**
 * @author Valber Carreiro
 *
 */
@Component
public class ValidaChavePessoaJuridica {
	
	private ChavePixService chavePixService;
	
	public ValidaChavePessoaJuridica(ChavePixService chavePixService) {
		this.chavePixService = chavePixService;
	}

	public boolean validarChaveCriacao(ChavePix chavePix) {
		// TODO Auto-generated method stub
		return false;
	}

}
