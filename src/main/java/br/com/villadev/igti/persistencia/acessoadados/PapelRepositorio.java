package br.com.villadev.igti.persistencia.acessoadados;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villadev.igti.persistencia.modelos.PapelModelo;

public interface PapelRepositorio extends JpaRepository<PapelModelo, Long> {

}
