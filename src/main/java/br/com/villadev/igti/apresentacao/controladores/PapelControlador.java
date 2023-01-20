package br.com.villadev.igti.apresentacao.controladores;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.villadev.igti.apresentacao.dtos.PapelDto;
import br.com.villadev.igti.negocio.servicos.PapelServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/papeis")
@RequiredArgsConstructor
public class PapelControlador {
	
	private final PapelServico papelServico;
	
	@PostMapping
	public ResponseEntity<PapelDto> salvar(@RequestBody PapelDto papelDto) {
		
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.build(papelServico.salvar(papelDto).getId()))
				.build();
	}
	
	@GetMapping
	public ResponseEntity<List<PapelDto>> listar() {
		List<PapelDto> papeis = papelServico.listar();
		return ResponseEntity.ok(papeis);
	}
	
	@PatchMapping("/{papelId}/permissoes/{permissaoId}")
	public ResponseEntity<PapelDto> adicionarPermissao(@PathVariable Long papelId, @PathVariable Long permissaoId) {
		
		PapelDto papelDto = papelServico.adicionarPermissao(papelId,permissaoId);
		
		if (papelDto.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
