package br.com.villadev.igti.apresentacao.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.villadev.igti.apresentacao.dtos.PermissaoDto;
import br.com.villadev.igti.negocio.servicos.PermissaoServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoControlador {
	
	private final PermissaoServico permissaoServico;
	
	@PostMapping
	public ResponseEntity<PermissaoDto> salvar(@RequestBody PermissaoDto papelDto) {
		
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.build(permissaoServico.salvar(papelDto).getId()))
				.build();
	}
	
	@GetMapping
	public ResponseEntity<List<PermissaoDto>> listar() {
		List<PermissaoDto> permissoes = permissaoServico.listar();
		return ResponseEntity.ok(permissoes);
	}

}
