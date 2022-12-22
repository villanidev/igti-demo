package br.com.villadev.igti.persistencia.acessoadados;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.villadev.igti.persistencia.modelos.InquilinoModelo;

public interface InquilinoRepositorio extends JpaRepository<InquilinoModelo, Long> {

}
