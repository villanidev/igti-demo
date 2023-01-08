package br.com.villadev.igti.persistencia.modelos;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_inquilino")
public class InquilinoModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	@Builder.Default
	private Boolean ativo = true;
	private String ipAcesso;
	private LocalDateTime ultimoAcesso;
	@OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	/*evita dependencia circular no lombok na hora de gerar equals and hashcode*/
	@EqualsAndHashCode.Exclude
	@Setter(value = AccessLevel.NONE)
	private Set<UsuarioModelo> usuarios = new HashSet<>();
	@OneToOne(mappedBy = "tenant")
	private OrganizacaoModelo organizacao;
	
	public void adicionarUsuario(final UsuarioModelo usuario) {
		usuarios.add(usuario);
		usuario.setTenant(this);
	}
	
	public void removerUsuario(final UsuarioModelo usuario) {
		usuarios.remove(usuario);
		usuario.setTenant(null);
	}
	
	public void adicionarUsuarios(final Collection<UsuarioModelo> usuarios) {
		usuarios.forEach(this::adicionarUsuario);
	}
	
	public void removerUsuarios(final Collection<UsuarioModelo> usuarios) {
		usuarios.forEach(this::removerUsuario);
	}
}
