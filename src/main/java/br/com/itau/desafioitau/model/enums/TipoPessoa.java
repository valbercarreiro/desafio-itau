/**
 * 
 */
package br.com.itau.desafioitau.model.enums;

/**
 * @author valbercarreiro
 *
 */
public enum TipoPessoa {
	
	PESSOA_JURIDICA("PJ"),
	PESSOA_FISICA("PF");

    private final String descricao;
    
	private TipoPessoa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
