package br.com.villadev.igti.apresentacao.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String email;
	private String password;
	private Boolean ativo;
}
