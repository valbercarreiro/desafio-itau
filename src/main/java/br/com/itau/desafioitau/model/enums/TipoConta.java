/**
 * 
 */
package br.com.itau.desafioitau.model.enums;

/**
 * @author valbercarreiro
 *
 */
public enum TipoConta {
	
	CORRENTE("corrente"),
	POUPANCA("poupança");

    private final String descricao;
    
	private TipoConta(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
