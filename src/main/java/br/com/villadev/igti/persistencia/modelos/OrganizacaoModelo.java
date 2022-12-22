package br.com.villadev.igti.persistencia.modelos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tbl_organizacao")
public class OrganizacaoModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "tenant_id")
	private InquilinoModelo tenant;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tbl_organizacao_funcionalidade",
			joinColumns = { @JoinColumn(name = "organizacao_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "funcionalidade_id") }
	)
	@Builder.Default
	@Setter(value = AccessLevel.NONE)
	private Set<FuncionalidadeModelo> funcionalidades = new HashSet<>();
	
	public void adicionarFuncionalidade(final FuncionalidadeModelo funcionalidade) {
		funcionalidades.add(funcionalidade);
	}
	
	public void removerFuncionalidade(final FuncionalidadeModelo funcionalidade) {
		funcionalidades.remove(funcionalidade);
	}
}
