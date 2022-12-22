package br.com.villadev.igti.persistencia.modelos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "tbl_papel")
public class PapelModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	@ManyToMany
	@JoinTable(
			name = "tbl_papel_permissao",
			joinColumns = { @JoinColumn(name = "papel_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "permissao_id") }
	)
	@Builder.Default
	@Setter(value = AccessLevel.NONE)
	private Set<PermissaoModelo> permissoes = new HashSet<>();
	
	public void adicionarPermissao(final PermissaoModelo permissao) {
		permissoes.add(permissao);
	}
	
	public void removerPermissao(final PermissaoModelo permissao) {
		permissoes.remove(permissao);
	}

}
