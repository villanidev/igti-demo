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

import br.com.villadev.igti.apresentacao.dtos.OrganizacaoDto;
import br.com.villadev.igti.negocio.servicos.OrganizacaoServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/organizacoes")
@RequiredArgsConstructor
public class OrganizacaoControlador {
	
	private final OrganizacaoServico organizacaoServico;
	
	@PostMapping
	public ResponseEntity<OrganizacaoDto> salvar(@RequestBody OrganizacaoDto orgDto) {
		
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.build(organizacaoServico.salvar(orgDto).getId()))
				.build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrganizacaoDto> buscarPorId(@PathVariable Long id) {
		OrganizacaoDto orgDto = organizacaoServico.buscarPorId(id);

		if (orgDto.getId() == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(orgDto);
	}
	
	@GetMapping
	public ResponseEntity<List<OrganizacaoDto>> listar() {
		List<OrganizacaoDto> organizacoes = organizacaoServico.listar();
		return ResponseEntity.ok(organizacoes);
	}
	
	@PatchMapping("/{orgId}/inquilinos/{funcionalidadeId}")
	public ResponseEntity<OrganizacaoDto> adicionarFuncionalidade(@PathVariable Long orgId, @PathVariable Long funcionalidadeId) {
		
		OrganizacaoDto orgDto = organizacaoServico.adicionarFuncionalidade(orgId,funcionalidadeId);
		
		if (orgDto.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PatchMapping("/{orgId}/inquilinos/{tenantId}")
	public ResponseEntity<OrganizacaoDto> adicionarTenant(@PathVariable Long orgId, @PathVariable Long tenantId) {
		
		OrganizacaoDto orgDto = organizacaoServico.adicionarTenant(orgId,tenantId);
		
		if (orgDto.getId() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
