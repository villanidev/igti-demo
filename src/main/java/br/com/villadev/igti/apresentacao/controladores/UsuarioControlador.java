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

import br.com.villadev.igti.apresentacao.dtos.UsuarioDto;
import br.com.villadev.igti.negocio.servicos.UsuarioServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioControlador {
	
	private final UsuarioServico usuarioServico;
	
	@PostMapping
	public ResponseEntity<UsuarioDto> salvar(@RequestBody UsuarioDto usuarioDto) {
		
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.build(usuarioServico.salvar(usuarioDto).getId()))
				.build();
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listar() {
		List<UsuarioDto> funcionalidades = usuarioServico.listar();
		return ResponseEntity.ok(funcionalidades);
	}
	
	@PatchMapping("/{usuarioId}/papeis/{papelId}")
	public ResponseEntity<UsuarioDto> adicionarPapel(@PathVariable Long usuarioId, @PathVariable Long papelId) {
		
		UsuarioDto usuarioDto = usuarioServico.adicionarPapel(usuarioId, papelId);
		
		if (usuarioDto.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
