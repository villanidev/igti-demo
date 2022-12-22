package br.com.villadev.igti.negocio.servicos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.persistencia.acessoadados.OrganizacaoRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizacaoServico {

	private final OrganizacaoRepositorio organizacaoRepositorio;
	
}
