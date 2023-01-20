package br.com.villadev.igti.negocio.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.apresentacao.dtos.PapelDto;
import br.com.villadev.igti.persistencia.acessoadados.PapelRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.PermissaoRepositorio;
import br.com.villadev.igti.persistencia.modelos.PapelModelo;
import br.com.villadev.igti.persistencia.modelos.PermissaoModelo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PapelServico {
	
	private final PapelRepositorio papelRepositorio;
	private final PermissaoRepositorio permissaoRepositorio;

	public PapelDto salvar(PapelDto papelDto) {
		PapelModelo papelBasico = PapelModelo.builder().nome(papelDto.getNome()).descricao(papelDto.getDescricao()).build();
		papelRepositorio.save(papelBasico);
		papelDto.setId(papelBasico.getId());
		return papelDto;
	}

	public List<PapelDto> listar() {
		return papelRepositorio.findAll().stream()
				.map(papelModelo -> PapelDto.builder()
						.id(papelModelo.getId())
						.nome(papelModelo.getNome())
						.descricao(papelModelo.getDescricao())
						.build())
				.toList();
	}

	public PapelDto adicionarPermissao(Long papelId, Long permissaoId) {
		Optional<PapelModelo> papelOpt = papelRepositorio.findById(papelId);
		Optional<PermissaoModelo> permissaoOpt = permissaoRepositorio.findById(permissaoId);
		
		if (papelOpt.isPresent() && permissaoOpt.isPresent()) {
			papelOpt.get().adicionarPermissao(permissaoOpt.get());
			papelRepositorio.save(papelOpt.get());
			return PapelDto.builder()
					.id(papelOpt.get().getId())
					.nome(papelOpt.get().getNome())
					.descricao(papelOpt.get().getDescricao())
					.build();
		}
		return new PapelDto();
	}

}
