package br.com.itau.desafioitau.resources.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoConta;
import br.com.itau.desafioitau.model.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Valber Carreiro
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChavePixUpdateRequest {
	
	@NotNull(message = "Campo Tipo Conta Obrigatório.")
	@Size(max = 10)
	private TipoConta tipoConta;

	@NotNull(message = "Campo Número Agência Obrigatório.")
	@Size(max = 4, message = "O tamanho máximo para o Número Agência é de 4 caracteres.")
	private Integer numAgencia;

	@NotNull(message = "Campo Número Conta Obrigatório.")
	@Size(max = 8, message = "O tamanho máximo para o Número Conta é de 8 caracteres.")
	private Long numConta;

	@NotNull(message = "Campo Nome Correntista Obrigatório.")
	@Size(max = 30, message = "O tamanho máximo para o Nome Correntista é de 30 caracteres.")
	private String nomeCorrentista;

	@Size(max = 45, message = "O tamanho máximo para o Sobrenome Correntista é de 45 caracteres.")
	private String sobrenomeCorrentista;

	@NotNull(message = "Campo Tipo Pessoa Obrigatório.")
	private TipoPessoa tipoPessoa;
	
	public ChavePix converter(ChavePix chavePix) {
		chavePix.setTipoConta(tipoConta);
		chavePix.setNumAgencia(numAgencia);
		chavePix.setNumConta(numConta);
		chavePix.setTipoPessoa(tipoPessoa);
		chavePix.setNomeCorrentista(nomeCorrentista);
		chavePix.setSobrenomeCorrentista(sobrenomeCorrentista);
		return chavePix;
	}
	
}
