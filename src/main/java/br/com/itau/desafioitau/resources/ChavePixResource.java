package br.com.itau.desafioitau.resources;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.model.enums.TipoChave;
import br.com.itau.desafioitau.resources.request.ChavePixRequest;
import br.com.itau.desafioitau.resources.response.ChavePixCadastroResponse;
import br.com.itau.desafioitau.resources.response.ChavePixResponse;
import br.com.itau.desafioitau.service.ChavePixService;

/**
 * @author Valber Carreiro
 *
 */
@RestController
@RequestMapping("/pix/chaves")
public class ChavePixResource {

	private ChavePixService service;
	
	public ChavePixResource(ChavePixService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ChavePixCadastroResponse> cadastrarChaves(@RequestBody @Valid ChavePixRequest req) {
		ChavePix cp = req.converter();
		cp = service.salvarChavePix(cp);
		return ResponseEntity.ok(new ChavePixCadastroResponse(cp.getId().toString()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ChavePixResponse> consultarPorId(@PathParam(value = "id") String id) {
		ChavePix chave = service.deletarPorId(id);
		return ResponseEntity.ok(ChavePixResponse.convertToResponse(chave)); 
	}
	
	@GetMapping
	public ResponseEntity<List<ChavePixResponse>> listarChavesPorTipo(@RequestParam(value = "id", required = false) String id, 
																		@RequestParam(value = "numAgencia", required = false) Integer numAgencia,
																		@RequestParam(value = "numConta", required = false) Long numConta,
																		@RequestParam(value = "tipoChave", required = false) TipoChave tipoChave,
																		@RequestParam(value = "nomeCorrentista", required = false) String nomeCorrentista) {
		List<ChavePix> chaves = service.listarChaves(id, numAgencia, numConta, tipoChave, nomeCorrentista);
		return ResponseEntity.ok(ChavePixResponse.convertListToListResponse(chaves)); 
	}
	
}
