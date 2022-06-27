package br.com.itau.desafioitau.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.itau.desafioitau.model.ChavePix;

public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {

}
