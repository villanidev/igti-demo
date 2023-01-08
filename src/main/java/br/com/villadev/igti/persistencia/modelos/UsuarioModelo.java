package br.com.villadev.igti.persistencia.modelos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "tbl_usuario")
public class UsuarioModelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean ativo;
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	@Setter(value = AccessLevel.NONE)
	private LocalDateTime criadoEm;
	private LocalDateTime ultimoAcesso;
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private LoginModelo login;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id")
	@JsonIgnore
	private InquilinoModelo tenant;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tbl_usuario_papel",
			joinColumns = { @JoinColumn(name = "usuario_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "papel_id") }
	)
	@Builder.Default
	@Setter(value = AccessLevel.NONE)
	private Set<PapelModelo> papeis = new HashSet<>();
	
	public void adicionarPapel(final PapelModelo papel) {
		papeis.add(papel);
	}
	
	public void removerPapel(final PapelModelo papel) {
		papeis.remove(papel);
	}

}
