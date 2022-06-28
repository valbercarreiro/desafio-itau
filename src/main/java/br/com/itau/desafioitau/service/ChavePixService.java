/**
 * 
 */
package br.com.itau.desafioitau.service;

import org.springframework.stereotype.Service;

import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoPessoa;
import br.com.itau.desafioitau.repository.ChavePixRepository;
import br.com.itau.desafioitau.validadores.ValidaChavePessoaFisica;
import br.com.itau.desafioitau.validadores.ValidaChavePessoaJuridica;
import br.com.itau.desafioitau.validadores.ValidaChavePix;

/**
 * @author Valber Carreiro
 *
 */
@Service
public class ChavePixService {
	
	private ChavePixRepository repository;
	
	private ValidaChavePix validaChave;

	public ChavePixService(ChavePixRepository repository) {
		this.repository = repository;
	}

	public ChavePix salvarChavePix(ChavePix chavePix) {
		
		if(chavePix.getTipoPessoa() == TipoPessoa.PESSOA_FISICA) {
			this.validaChave = new ValidaChavePessoaFisica(this);
		} else {
			this.validaChave = new ValidaChavePessoaJuridica(this);
		}
		this.validaChave.validaChavePix(chavePix);
		
		chavePix = this.repository.save(chavePix);
		return chavePix;
	}
	
	public Integer countByValorChave(String valorChave) {
		return repository.countByValorChave(valorChave);
	}
	
	public Integer countByNumAgenciaNumConta(Integer numAgencia, Long numConta) {
		return repository.countByNumAgenciaNumConta(numAgencia, numConta);
	}
}
