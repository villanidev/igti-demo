package br.com.villadev.igti.persistencia.modelos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_fornecedor")
public class FornecedorModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeFantasia;
	private String cnpj;
	private String codigo;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataAtualizacao;
	@OneToOne(cascade = CascadeType.ALL)
	private InformacaoBancariaModelo informacaoBancaria;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fornecedor_id", nullable = false)
	@Builder.Default
	private Set<EnderecoModelo> enderecos = new HashSet<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fornecedor_id", nullable = false)
	@Builder.Default
	private Set<ContatoModelo> contatos = new HashSet<>();
	
}
