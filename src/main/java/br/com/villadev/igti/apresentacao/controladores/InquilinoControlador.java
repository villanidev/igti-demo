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

import br.com.villadev.igti.apresentacao.dtos.InquilinoDto;
import br.com.villadev.igti.negocio.servicos.InquilinoServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inquilinos")
@RequiredArgsConstructor
public class InquilinoControlador {
	
	private final InquilinoServico inquilinoServico;
	
	@PostMapping
	public ResponseEntity<InquilinoDto> salvar(@RequestBody InquilinoDto inquilinoDto) {
		
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.build(inquilinoServico.salvar(inquilinoDto).getId()))
				.build();
	}
	
	@GetMapping
	public ResponseEntity<List<InquilinoDto>> listar() {
		List<InquilinoDto> funcionalidades = inquilinoServico.listar();
		return ResponseEntity.ok(funcionalidades);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<InquilinoDto> mudarStatus(@PathVariable Long id, @RequestBody Boolean ativo) {
		
		InquilinoDto inquilinoDto = inquilinoServico.mudarStatus(id, ativo);
		
		if (inquilinoDto.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.build(inquilinoDto.getId()))
				.build();
	}
	
	@PatchMapping("/{tenantId}/usuarios/{usuarioId}")
	public ResponseEntity<InquilinoDto> adicionarUsuario(@PathVariable Long tenantId, @PathVariable Long usuarioId) {
		
		InquilinoDto inquilinoDto = inquilinoServico.adicionarUsuario(tenantId,usuarioId);
		
		if (inquilinoDto.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
