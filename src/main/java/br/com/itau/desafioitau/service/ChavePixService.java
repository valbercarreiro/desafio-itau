/**
 * 
 */
package br.com.itau.desafioitau.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.itau.desafioitau.exceptions.ChavePixNaoEncontradaException;
import br.com.itau.desafioitau.exceptions.ErroNegocioException;
import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoChave;
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
		
		if(chavePix.getTipoPessoa() == TipoPessoa.F) {
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
	
	public Integer countByNumAgenciaAndNumConta(Integer numAgencia, Long numConta) {
		return repository.countByNumAgenciaAndNumConta(numAgencia, numConta);
	}
	
	public ChavePix consultaPorId(String id) {
		Optional<ChavePix> opt = repository.findById(UUID.fromString(id));
		if(!opt.isPresent()) {
			throw new ChavePixNaoEncontradaException("Nenhum registro encontrado para o ID informado.");
		}
		return opt.get();
	}

	public List<ChavePix> listarChaves(String id, Integer numAgencia, Long numConta, TipoChave tipoChave, String nomeCorrentista) {
		
		if(regraValidaConsultaId(id, numAgencia, numConta, tipoChave, nomeCorrentista)) {
			throw new ErroNegocioException("Favor selecione apenas ou ID ou os demais como filtro, a combinação de ID com outro campo invalida a ação.");
		} else if(id == null && regraValidaConsulta(numAgencia, numConta)) {
			throw new ErroNegocioException("O preenchimento de um dos campos NumAgencia/NumConta, torna o preenchimento do outro obrigatório.");
		}
		
		List<ChavePix> lista = new ArrayList<ChavePix>();
		
		if(id != null) {
			lista.add(consultaPorId(id));
		} else {
			//TODO voltar aqui depois p implementar o fluxo para os demais filtros
		}
		
		if(lista == null || lista.isEmpty()) {
			throw new ChavePixNaoEncontradaException("Nenhum registro encontrado para os parâmetros informados.");
		}
		return lista;
	}
	
	private boolean regraValidaConsulta(Integer numAgencia, Long numConta) {
		return (numAgencia != null && numConta == null) || (numAgencia == null && numConta != null);
	}

	private boolean regraValidaConsultaId(String id, Integer numAgencia, Long numConta, TipoChave tipoChave,
			String nomeCorrentista) {
		return (id != null && (numAgencia != null || numConta != null || tipoChave != null || nomeCorrentista != null));
	}

	public ChavePix deletarPorId(String id) {
		ChavePix chavePix = consultaPorId(id);
		if(chavePix.getDataInativacao() != null) {
			throw new ErroNegocioException("Chave informada já desativada.");
		}
		chavePix.setDataInativacao(LocalDateTime.now());
		chavePix = repository.save(chavePix);
		return chavePix;
	}

	public ChavePix alterarChavePix(ChavePix chavePix) {
		if(chavePix.getDataInativacao() != null) {
			throw new ErroNegocioException("Não é possível alterar uma chave desativada.");
		}
		
		if(chavePix.getTipoPessoa() == TipoPessoa.F) {
			this.validaChave = new ValidaChavePessoaFisica(this);
		} else {
			this.validaChave = new ValidaChavePessoaJuridica(this);
		}
		this.validaChave.validaNomeSobrenome(chavePix);
		
		return repository.save(chavePix);
	}
	
}
