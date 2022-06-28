/**
 * 
 */
package br.com.itau.desafioitau.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.itau.desafioitau.exceptions.ChaveJaExistenteException;
import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoPessoa;
import br.com.itau.desafioitau.repository.ChavePixRepository;
import br.com.itau.desafioitau.validadores.ValidaChavePessoaFisica;
import br.com.itau.desafioitau.validadores.ValidaChavePessoaJuridica;

/**
 * @author Valber Carreiro
 *
 */
@Service
public class ChavePixService {
	
	private ChavePixRepository repository;
	
	private ValidaChavePessoaFisica validaPF;
	
	private ValidaChavePessoaJuridica validaPJ;

	public ChavePixService(ChavePixRepository repository, ValidaChavePessoaFisica validaPF, ValidaChavePessoaJuridica validaPJ) {
		this.repository = repository;
		this.validaPF = validaPF;
		this.validaPJ = validaPJ;
	}

	public String salvarChavePix(ChavePix chavePix) {
		
		boolean chaveValida = false;
		if(chavePix.getTipoPessoa() == TipoPessoa.PESSOA_FISICA) {
			chaveValida = this.validaPF.validarChaveCriacao(chavePix);
		} else {
			chaveValida = this.validaPJ.validarChaveCriacao(chavePix);
		}
		
		if(!chaveValida) {
			throw new ChaveJaExistenteException();
		}
		
		chavePix = this.repository.save(chavePix);
		return chavePix.getValorChave();
	}
	
	public Integer countByValorChave(String valorChave) {
		return repository.countByValorChave(valorChave);
	}
}
