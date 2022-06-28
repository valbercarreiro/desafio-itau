/**
 * 
 */
package br.com.itau.desafioitau.model.enums;

/**
 * @author Valber Carreiro
 *
 */
public enum TipoChave {
	
	CELULAR("celular"),
	EMAIL("email"),
	CPF("cpf"),
	CNPJ("cnpj"),
	ALEATORIO("aleatorio");

    private final String descricao;
    
	private TipoChave(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
