package br.com.villadev.igti.persistencia.modelos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	@Column(nullable = false)
	private String nomeFantasia;
	@Column(nullable = false)
	private String cnpj;
	private String codigo;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	private LocalDateTime dataAtualizacao;
	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	private InformacaoBancariaModelo informacaoBancaria;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fornecedor_id", nullable = false)
	@Column(nullable = false)
	@Builder.Default
	@Setter(value = AccessLevel.NONE)
	private Set<EnderecoModelo> enderecos = new HashSet<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "fornecedor_id", nullable = false)
	@Column(nullable = false)
	@Builder.Default
	@Setter(value = AccessLevel.NONE)
	private Set<ContatoModelo> contatos = new HashSet<>();
	
	public void adicionarEndereco(final EnderecoModelo endereco) {
		this.enderecos.add(endereco);
	}
	
	public void removerEndereco(final EnderecoModelo endereco) {
		this.enderecos.add(endereco);
	}
	
	public void adicionarEnderecos(final Collection<EnderecoModelo> enderecos) {
		enderecos.forEach(this::adicionarEndereco);
	}
	
	public void removerEnderecos(final Collection<EnderecoModelo> enderecos) {
		enderecos.forEach(this::removerEndereco);
	}
	
	public void adicionarContato(final ContatoModelo contato) {
		this.contatos.add(contato);
	}
	
	public void removerContato(final ContatoModelo contato) {
		this.contatos.add(contato);
	}
	
	public void adicionarContatos(final Collection<ContatoModelo> enderecos) {
		contatos.forEach(this::adicionarContato);
	}
	
	public void removerContatos(final Collection<ContatoModelo> enderecos) {
		contatos.forEach(this::removerContato);
	}
	
}
