package br.com.villadev.igti.negocio.servicos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.apresentacao.dtos.InquilinoDto;
import br.com.villadev.igti.persistencia.acessoadados.InquilinoRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.UsuarioRepositorio;
import br.com.villadev.igti.persistencia.modelos.InquilinoModelo;
import br.com.villadev.igti.persistencia.modelos.UsuarioModelo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InquilinoServico {

	private final InquilinoRepositorio inquilinoRepositorio;
	private final UsuarioRepositorio usuarioRepositorio;

	public InquilinoDto salvar(InquilinoDto inquilinoDto) {
		InquilinoModelo inquilino1 = InquilinoModelo.builder()
				.codigo(UUID.randomUUID().toString())
				.ipAcesso(inquilinoDto.getIpAcesso())
				.build();
		
		inquilinoRepositorio.save(inquilino1);
		inquilinoDto.setId(inquilino1.getId());
		 return inquilinoDto;
		
	}

	public List<InquilinoDto> listar() {
		return inquilinoRepositorio.findAll().stream()
				.map(inquilinoModelo -> InquilinoDto.builder()
						.id(inquilinoModelo.getId())
						.codigo(inquilinoModelo.getCodigo())
						.ipAcesso(inquilinoModelo.getIpAcesso())
						.ativo(inquilinoModelo.getAtivo())
						.build())
				.toList();
	}

	public InquilinoDto mudarStatus(Long id, Boolean ativo) {
		Optional<InquilinoModelo> optInquilino = inquilinoRepositorio.findById(id);
		
		if (optInquilino.isPresent()) {
			optInquilino.get().setAtivo(ativo);
			inquilinoRepositorio.save(optInquilino.get());
		}
		
		return new InquilinoDto();
	}

	public InquilinoDto adicionarUsuario(Long tenantId, Long usuarioId) {
		Optional<InquilinoModelo> tenantOpt = inquilinoRepositorio.findById(tenantId);
		Optional<UsuarioModelo> usuarioOpt = usuarioRepositorio.findById(usuarioId);
		
		if (tenantOpt.isPresent() && usuarioOpt.isPresent()) {
			tenantOpt.get().adicionarUsuario(usuarioOpt.get());
			inquilinoRepositorio.save(tenantOpt.get());
			return InquilinoDto.builder()
					.id(tenantOpt.get().getId())
					.codigo(tenantOpt.get().getCodigo())
					.ipAcesso(tenantOpt.get().getIpAcesso())
					.ativo(tenantOpt.get().getAtivo())
					.build();
		}
		return new InquilinoDto();
	}
	
}
