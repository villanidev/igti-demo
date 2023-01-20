package br.com.villadev.igti.negocio.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.apresentacao.dtos.UsuarioDto;
import br.com.villadev.igti.persistencia.acessoadados.PapelRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.UsuarioRepositorio;
import br.com.villadev.igti.persistencia.modelos.LoginModelo;
import br.com.villadev.igti.persistencia.modelos.PapelModelo;
import br.com.villadev.igti.persistencia.modelos.UsuarioModelo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServico {

	private final UsuarioRepositorio usuarioRepositorio;
	private final PapelRepositorio papelRepositorio;

	public UsuarioDto salvar(UsuarioDto usuarioDto) {
		LoginModelo login1 = LoginModelo.builder().email(usuarioDto.getEmail()).password(usuarioDto.getPassword()).build();
		UsuarioModelo usuario1 = UsuarioModelo.builder().nome(usuarioDto.getNome()).ativo(true)
				.login(login1).build();
		
		usuarioRepositorio.save(usuario1);
		return usuarioDto;
	}

	public List<UsuarioDto> listar() {
		return usuarioRepositorio.findAll().stream()
				.map(usuarioModelo -> UsuarioDto.builder()
						.id(usuarioModelo.getId())
						.ativo(usuarioModelo.getAtivo())
						.email(usuarioModelo.getLogin().getEmail())
						.nome(usuarioModelo.getNome())
						.build())
				.toList();
	}

	public UsuarioDto adicionarPapel(Long usuarioId, Long papelId) {
		Optional<UsuarioModelo> usuarioOpt = usuarioRepositorio.findById(usuarioId);
		Optional<PapelModelo> papelOpt = papelRepositorio.findById(papelId);
		
		if (papelOpt.isPresent() && usuarioOpt.isPresent()) {
			usuarioOpt.get().adicionarPapel(papelOpt.get());
			usuarioRepositorio.save(usuarioOpt.get());
			return UsuarioDto.builder()
					.id(usuarioOpt.get().getId())
					.ativo(usuarioOpt.get().getAtivo())
					.email(usuarioOpt.get().getLogin().getEmail())
					.nome(usuarioOpt.get().getNome())
					.build();
		}
		return new UsuarioDto();
	}
	
}
