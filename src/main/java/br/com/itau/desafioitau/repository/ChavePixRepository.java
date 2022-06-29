package br.com.itau.desafioitau.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoChave;

public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {
	
	Integer countByValorChave(String valorChave);

	Integer countByNumAgenciaAndNumConta(Integer numAgencia, Long numConta);
	
	List<ChavePix> findByTipoChave(TipoChave tipoChave);
	
}
