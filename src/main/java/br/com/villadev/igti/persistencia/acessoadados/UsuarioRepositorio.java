package br.com.villadev.igti.persistencia.acessoadados;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villadev.igti.persistencia.modelos.UsuarioModelo;

public interface UsuarioRepositorio extends JpaRepository<UsuarioModelo, Long> {

}
