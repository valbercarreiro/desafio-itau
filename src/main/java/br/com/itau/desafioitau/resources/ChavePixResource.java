package br.com.itau.desafioitau.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.desafioitau.model.ChavePix;
import br.com.itau.desafioitau.resources.request.ChavePixRequest;
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
	public ResponseEntity<String> cadastrarChaves(@RequestBody @Validated ChavePixRequest req) {
		ChavePix cp = req.converter();
		try {
			cp = service.salvarChavePix(cp);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(cp.getId().toString());
	}
	
}
