package br.com.villadev.igti.negocio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.apresentacao.dtos.FuncionalidadeDto;
import br.com.villadev.igti.persistencia.acessoadados.FuncionalidadeRepositorio;
import br.com.villadev.igti.persistencia.modelos.FuncionalidadeModelo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FuncionalidadeServico {

	private final FuncionalidadeRepositorio funcionalidadeRepositorio;

	public FuncionalidadeDto salvar(FuncionalidadeDto funcDto) {
		FuncionalidadeModelo funcionalidade1 = FuncionalidadeModelo.builder()
				.nome(funcDto.getNome())
				.descricao(funcDto.getDescricao()).build();
		
		funcionalidadeRepositorio.save(funcionalidade1);
		funcDto.setId(funcionalidade1.getId());
		return funcDto;
		
	}

	public List<FuncionalidadeDto> listar() {
		return funcionalidadeRepositorio.findAll().stream()
				.map(funcModelo -> FuncionalidadeDto.builder()
						.id(funcModelo.getId())
						.nome(funcModelo.getNome())
						.descricao(funcModelo.getDescricao())
						.build())
				.toList();
	}
	
}
