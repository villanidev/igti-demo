package br.com.villadev.igti.apresentacao.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.villadev.igti.apresentacao.dtos.FuncionalidadeDto;
import br.com.villadev.igti.negocio.servicos.FuncionalidadeServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/funcionalidades")
@RequiredArgsConstructor
public class FuncionalidadeControlador {
	
	private final FuncionalidadeServico funcionalidadeServico;
	
	@PostMapping
	public ResponseEntity<FuncionalidadeDto> salvar(@RequestBody FuncionalidadeDto funcDto) {
		
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.build(funcionalidadeServico.salvar(funcDto).getId()))
				.build();
	}
	
	@GetMapping
	public ResponseEntity<List<FuncionalidadeDto>> listar() {
		List<FuncionalidadeDto> funcionalidades = funcionalidadeServico.listar();
		return ResponseEntity.ok(funcionalidades);
	}

}
