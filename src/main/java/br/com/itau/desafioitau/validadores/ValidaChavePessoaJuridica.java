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
public class ValidaChavePessoaJuridica extends ValidaChavePix {
	
	private static final Integer MAX_CHAVES_CONTA_AGENCIA = 20;
	
	private ChavePixService chavePixService;
	
	public ValidaChavePessoaJuridica(ChavePixService chavePixService) {
		super(chavePixService);
		this.chavePixService = chavePixService;
	}
	
	public boolean validarChaveCriacao(ChavePix chavePix) {

		Integer quantidade = this.chavePixService.countByNumAgenciaNumConta(chavePix.getNumAgencia(), chavePix.getNumConta());
		
		if(quantidade < MAX_CHAVES_CONTA_AGENCIA) {
			boolean result = true;
			
			switch (chavePix.getTipoChave()) {
				case CELULAR:
					result = new ValidaChaveCelular().validacoesCelular(chavePix.getValorChave());
					break;
				case EMAIL:
					result = new ValidaChaveEmail().validacoesEmail(chavePix.getValorChave());
					break;
				case CNPJ:
					result = new ValidaChaveCnpj().validacoesCnpj(chavePix.getValorChave());
					break;
				case ALEATORIO:
					result = new ValidaChaveAleatoria().validacoesChaveAleatoria(chavePix.getValorChave());
					break;
				default:
					break;
				}
			
			return result;
		}
		
		return false;
	}

}
