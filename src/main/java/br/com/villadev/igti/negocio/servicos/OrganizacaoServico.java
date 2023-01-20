package br.com.villadev.igti.negocio.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.apresentacao.dtos.OrganizacaoDto;
import br.com.villadev.igti.persistencia.acessoadados.FuncionalidadeRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.InquilinoRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.OrganizacaoRepositorio;
import br.com.villadev.igti.persistencia.modelos.FuncionalidadeModelo;
import br.com.villadev.igti.persistencia.modelos.InquilinoModelo;
import br.com.villadev.igti.persistencia.modelos.OrganizacaoModelo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizacaoServico {

	private final OrganizacaoRepositorio organizacaoRepositorio;
	private final FuncionalidadeRepositorio funcionalidadeRepositorio;
	private final InquilinoRepositorio inquilinoRepositorio;
	
	public OrganizacaoDto salvar(OrganizacaoDto orgDto) {
		OrganizacaoModelo organizacao = OrganizacaoModelo.builder()
				.nome(orgDto.getNome()).build();
		organizacaoRepositorio.save(organizacao);
		orgDto.setId(organizacao.getId());
		return orgDto;
	}

	public OrganizacaoDto buscarPorId(Long id) {
		Optional<OrganizacaoModelo> orgOpt = organizacaoRepositorio.findById(id);
		
		if (orgOpt.isPresent()) {
			return OrganizacaoDto.builder().id(orgOpt.get().getId()).nome(orgOpt.get().getNome()).build();
		}
		
		return new OrganizacaoDto();
	}

	public List<OrganizacaoDto> listar() {
		return organizacaoRepositorio.findAll().stream()
				.map(orgModelo -> OrganizacaoDto.builder()
						.id(orgModelo.getId())
						.nome(orgModelo.getNome())
						.build())
				.toList();
	}

	public OrganizacaoDto adicionarFuncionalidade(Long orgId, Long funcionalidadeId) {
		Optional<FuncionalidadeModelo> funcOpt = funcionalidadeRepositorio.findById(funcionalidadeId);
		Optional<OrganizacaoModelo> orgOpt = organizacaoRepositorio.findById(orgId);
		
		if (funcOpt.isPresent() && orgOpt.isPresent()) {
			orgOpt.get().adicionarFuncionalidade(funcOpt.get());
			organizacaoRepositorio.save(orgOpt.get());
			return OrganizacaoDto.builder().id(orgOpt.get().getId()).nome(orgOpt.get().getNome()).build();
		}
		return new OrganizacaoDto();
	}

	public OrganizacaoDto adicionarTenant(Long orgId, Long tenantId) {
		Optional<InquilinoModelo> tenantOpt = inquilinoRepositorio.findById(tenantId);
		Optional<OrganizacaoModelo> orgOpt = organizacaoRepositorio.findById(orgId);
		
		if (tenantOpt.isPresent() && orgOpt.isPresent()) {
			orgOpt.get().setTenant(tenantOpt.get());
			organizacaoRepositorio.save(orgOpt.get());
			return OrganizacaoDto.builder().id(orgOpt.get().getId()).nome(orgOpt.get().getNome()).build();
		}
		return new OrganizacaoDto();
	}
	
}
