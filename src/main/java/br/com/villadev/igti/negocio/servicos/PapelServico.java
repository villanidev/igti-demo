package br.com.villadev.igti.negocio.servicos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.villadev.igti.persistencia.acessoadados.PapelRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PapelServico {
	
	private final PapelRepositorio papelRepositorio;

}
