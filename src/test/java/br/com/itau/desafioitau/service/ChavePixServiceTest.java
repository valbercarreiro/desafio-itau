package br.com.itau.desafioitau.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.itau.desafioitau.exceptions.ChavePixNaoEncontradaException;
import br.com.itau.desafioitau.exceptions.ErroNegocioException;
import br.com.itau.desafioitau.exceptions.ErroValidacaoChaveException;
import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoChave;
import br.com.itau.desafioitau.model.enums.TipoConta;
import br.com.itau.desafioitau.model.enums.TipoPessoa;
import br.com.itau.desafioitau.repository.ChavePixRepository;

@ExtendWith(MockitoExtension.class)
public class ChavePixServiceTest {
	
	@InjectMocks
	private ChavePixService serviceMock;
	
	@Mock
	private ChavePixRepository repositorio;
	
	private ChavePix chavePix;
	
	private ChavePix chavePixSalva;
	
	private ChavePix chavePixAlterada;
	
	private ChavePix chavePixInativada;

	@BeforeEach
	void setUp() throws Exception {
		this.chavePix = new ChavePix(null, TipoChave.CELULAR, "+55 83 999999999", TipoConta.CORRENTE, 1234, 123456l, "Correntista", "Sobrenome Correntista", TipoPessoa.F, null, null, null);
		this.chavePixSalva = new ChavePix(UUID.randomUUID(), TipoChave.CELULAR, "+55 83 999999999", TipoConta.CORRENTE, 1234, 123456l, "Correntista", "Sobrenome Correntista", TipoPessoa.F, LocalDateTime.now(), null, null);
		this.chavePixAlterada = new ChavePix(UUID.randomUUID(), TipoChave.CELULAR, "+55 83 999999999", TipoConta.CORRENTE, 1234, 123456l, "Correntista2", "Sobrenome Correntista Alterado", TipoPessoa.F, LocalDateTime.now(), LocalDateTime.now(), null);
		this.chavePixInativada = new ChavePix(UUID.randomUUID(), TipoChave.CELULAR, "+55 83 999999999", TipoConta.CORRENTE, 1234, 123456l, "Correntista", "Sobrenome Correntista", TipoPessoa.F, LocalDateTime.now(), null, LocalDateTime.now());
	}
	
	@Test
	void salvaChavePixSucessoTest() {
		
		when(this.repositorio.save(this.chavePix)).thenReturn(this.chavePixSalva);
		
		ChavePix chavePixRetorno = this.serviceMock.salvarChavePix(chavePix);
		
		assertEquals(this.chavePixSalva.getId().toString(), chavePixRetorno.getId().toString());
		assertNotNull(chavePixRetorno.getDataCriacao());
		
	}
	
	@Test
	void salvaChavePixCelularInvalidaTest() {
		
		this.chavePix.setValorChave("55 83 999999999");
		assertThrows(ErroValidacaoChaveException.class, () -> this.serviceMock.salvarChavePix(this.chavePix));		
		
	}
	
	@Test
	void salvaChavePixAleatoriaInvalidaTest() {
		
		this.chavePix.setTipoChave(TipoChave.ALEATORIO);
		this.chavePix.setValorChave("org.opentest4j.AssertionFailedError: Unexpected exception type thrown ==> expected: <");
		assertThrows(ErroValidacaoChaveException.class, () -> this.serviceMock.salvarChavePix(this.chavePix));		
		
	}
	
	@Test
	void salvaChavePixEmailInvalidaTest() {
		
		this.chavePix.setTipoChave(TipoChave.EMAIL);
		this.chavePix.setValorChave("emailsemarroba.com");
		assertThrows(ErroValidacaoChaveException.class, () -> this.serviceMock.salvarChavePix(this.chavePix));		
		
	}
	
	@Test
	void salvaChavePixCPFInvalidaTest() {
		
		this.chavePix.setTipoChave(TipoChave.CPF);
		this.chavePix.setValorChave("11111111110");
		assertThrows(ErroValidacaoChaveException.class, () -> this.serviceMock.salvarChavePix(this.chavePix));		
		
	}
	
	@Test
	void salvaChavePixCNPJInvalidaTest() {
		
		this.chavePix.setTipoPessoa(TipoPessoa.J);
		this.chavePix.setTipoChave(TipoChave.CNPJ);
		this.chavePix.setValorChave("11111111111110");
		assertThrows(ErroValidacaoChaveException.class, () -> this.serviceMock.salvarChavePix(this.chavePix));		
		
	}
	
	@Test
	void alteraChavePixSucessoTest() {
		
		when(this.repositorio.save(any())).thenReturn(this.chavePixAlterada);
		
		ChavePix chavePixRetorno = this.serviceMock.alterarChavePix(chavePixSalva);
		
		assertEquals(this.chavePixAlterada.getNomeCorrentista(), chavePixRetorno.getNomeCorrentista());
		assertEquals(this.chavePixAlterada.getSobrenomeCorrentista(), chavePixRetorno.getSobrenomeCorrentista());
		assertNotNull(chavePixRetorno.getDataAtualizacao());
		
	}
	
	@Test
	void consultaChavePixTest() {
		
		when(this.repositorio.findById(any())).thenReturn(Optional.of(this.chavePixSalva));
		
		ChavePix chavePixRetorno = this.serviceMock.consultaPorId(UUID.randomUUID().toString());
		
		assertEquals(this.chavePixSalva.getNomeCorrentista(), chavePixRetorno.getNomeCorrentista());
		assertEquals(this.chavePixSalva.getSobrenomeCorrentista(), chavePixRetorno.getSobrenomeCorrentista());
		assertNotNull(chavePixRetorno.getDataCriacao());
		
	}
	
	@Test
	void consultaChavePixInativaTest() {
		
		when(this.repositorio.findById(any())).thenReturn(Optional.empty());
		assertThrows(ChavePixNaoEncontradaException.class, () -> this.serviceMock.consultaPorId(UUID.randomUUID().toString()));
		
	}
	
	@Test
	void inativaChavePixSucessoTest() {
		
		when(this.repositorio.findById(any())).thenReturn(Optional.of(this.chavePixSalva));
		when(this.repositorio.save(any())).thenReturn(this.chavePixInativada);
		
		ChavePix chavePixRetorno = this.serviceMock.deletarPorId(chavePixSalva.getId().toString());
		
		assertEquals(this.chavePixInativada.getId().toString(), chavePixRetorno.getId().toString());
		assertNotNull(chavePixRetorno.getDataInativacao());
		
	}
	
	@Test
	void inativaChavePixJaInativadaTest() {
		
		when(this.repositorio.findById(any())).thenReturn(Optional.of(this.chavePixInativada));
		
		assertThrows(ErroNegocioException.class, () -> this.serviceMock.deletarPorId(chavePixSalva.getId().toString()));
		
	}
	
	@Test
	void listaChavePixSucessoTest() {
		
		List<ChavePix> chavesCadastradas = List.of(chavePixSalva);
		when(this.repositorio.findById(any())).thenReturn(Optional.of(this.chavePixSalva));
		
		List<ChavePix> listaRetorno = this.serviceMock.listarChaves(this.chavePixSalva.getId().toString(), null, null, null, null);
		assertEquals(chavesCadastradas.size(), listaRetorno.size());
		
	}
	
	@Test
	void listaChavePixIdEOutroCampoPreenchidoTest() {
		
		assertThrows(ErroNegocioException.class, () -> this.serviceMock.listarChaves(this.chavePixSalva.getId().toString(), 1234, 123456l, null, null));
		
	}
	
	@Test
	void listaChavePixSemIdComNumAgenciaPreenchidoENumContaVazioTest() {
		
		assertThrows(ErroNegocioException.class, () -> this.serviceMock.listarChaves(null, 1234, null, null, null));
		
	}
	
	@Test
	void listaChavePixSemIdComNumContaPreenchidoENumAgenciaVazioTest() {
		
		assertThrows(ErroNegocioException.class, () -> this.serviceMock.listarChaves(null, null, 123456l, null, null));
		
	}

}
