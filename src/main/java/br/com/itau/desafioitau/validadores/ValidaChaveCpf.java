package br.com.itau.desafioitau.validadores;

import br.com.itau.desafioitau.exceptions.ErroValidacaoChaveException;

/**
 * @author Valber Carreiro
 *
 */
public class ValidaChaveCpf {

	public void validacoesCpf(String valorChave) {
		if(!isValidarDigitoVerificadorModulo11Base10(valorChave)) {
			throw new ErroValidacaoChaveException("Valor de chave CPF inválido.");
		}
	}
	
	private boolean isValidarDigitoVerificadorModulo11Base10(String cpf) {
		if (cpf.length() != 11)
            return false;
        String numDig = cpf.substring(0, 9);
        return gerarDigitoVerificadorModulo11Base10(numDig).equals(cpf.substring(9, 11));
	}
	
	private String gerarDigitoVerificadorModulo11Base10(String num){
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();		
	}
}
