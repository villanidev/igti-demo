package br.com.villadev.igti.negocio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.apresentacao.dtos.PermissaoDto;
import br.com.villadev.igti.persistencia.acessoadados.PermissaoRepositorio;
import br.com.villadev.igti.persistencia.modelos.PermissaoModelo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissaoServico {
	
	private final PermissaoRepositorio permissaoRepositorio;

	public PermissaoDto salvar(PermissaoDto permissaoDto) {
		PermissaoModelo papelBasico = PermissaoModelo.builder()
				.nome(permissaoDto.getNome()).descricao(permissaoDto.getDescricao())
				.build();
		permissaoRepositorio.save(papelBasico);
		permissaoDto.setId(papelBasico.getId());
		return permissaoDto;
	}

	public List<PermissaoDto> listar() {
		return permissaoRepositorio.findAll().stream()
				.map(permissaoModelo -> PermissaoDto.builder()
						.id(permissaoModelo.getId())
						.nome(permissaoModelo.getNome())
						.descricao(permissaoModelo.getDescricao())
						.build())
				.toList();
	}

}
