package br.com.villadev.igti.persistencia.acessoadados;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villadev.igti.persistencia.modelos.FornecedorModelo;

public interface FornecedorRepositorio extends JpaRepository<FornecedorModelo, Long> {

}
