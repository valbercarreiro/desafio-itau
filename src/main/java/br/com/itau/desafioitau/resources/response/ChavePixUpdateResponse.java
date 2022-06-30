package br.com.itau.desafioitau.resources.response;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoChave;
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
public class ChavePixUpdateResponse {
	
	private UUID id;
	
	private TipoChave tipoChave;
	
	private String valorChave;
	
	private TipoConta tipoConta;

	private Integer numAgencia;

	private Long numConta;

	private String nomeCorrentista;
	
	private TipoPessoa tipoPessoa;

	private String sobrenomeCorrentista;

	private LocalDateTime dataCriacao;
	
	public static ChavePixUpdateResponse convertToResponse(ChavePix chavePix) {
		return new ChavePixUpdateResponse(chavePix.getId(), chavePix.getTipoChave(), chavePix.getValorChave(), 
				chavePix.getTipoConta(), chavePix.getNumAgencia(), chavePix.getNumConta(), 
				chavePix.getNomeCorrentista(), chavePix.getTipoPessoa(), chavePix.getSobrenomeCorrentista(), 
				chavePix.getDataCriacao());
	}
}
