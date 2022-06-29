/**
 * 
 */
package br.com.itau.desafioitau.validadores;

import org.springframework.stereotype.Component;

import br.com.itau.desafioitau.exceptions.ErroValidacaoChaveException;
import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.service.ChavePixService;

/**
 * @author Valber Carreiro
 *
 */
@Component
public class ValidaChavePessoaJuridica extends ValidaChavePix {
	
	private static final Integer MAX_CHAVES_CONTA_AGENCIA = 20;
	
	private ChavePixService chavePixService;
	
	public ValidaChavePessoaJuridica(ChavePixService chavePixService) {
		super(chavePixService);
		this.chavePixService = chavePixService;
	}
	
	public void validarChaveCriacao(ChavePix chavePix) {

		Integer quantidade = this.chavePixService.countByNumAgenciaAndNumConta(chavePix.getNumAgencia(), chavePix.getNumConta());
		
		if(quantidade < MAX_CHAVES_CONTA_AGENCIA) {
			
			switch (chavePix.getTipoChave()) {
				case CELULAR:
					new ValidaChaveCelular().validacoesCelular(chavePix.getValorChave());
					break;
				case EMAIL:
					new ValidaChaveEmail().validacoesEmail(chavePix.getValorChave());
					break;
				case CNPJ:
					new ValidaChaveCnpj().validacoesCnpj(chavePix.getValorChave());
					break;
				case ALEATORIO:
					new ValidaChaveAleatoria().validacoesChaveAleatoria(chavePix.getValorChave());
					break;
				default:
					break;
				}
			
		}
		
		throw new ErroValidacaoChaveException("Quantidade de chaves já atingida.");
	}

}
