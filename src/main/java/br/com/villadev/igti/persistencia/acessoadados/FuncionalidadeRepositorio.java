package br.com.villadev.igti.persistencia.acessoadados;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villadev.igti.persistencia.modelos.FuncionalidadeModelo;

public interface FuncionalidadeRepositorio extends JpaRepository<FuncionalidadeModelo, Long> {

}
