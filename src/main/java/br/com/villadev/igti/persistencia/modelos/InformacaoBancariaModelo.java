package br.com.villadev.igti.persistencia.modelos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "tbl_informacao_bancaria")
public class InformacaoBancariaModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "informacao_bancaria_id", nullable = false)
	@Builder.Default
	@Setter(value = AccessLevel.NONE)
	private Set<ContaBancariaModelo> contasBancarias = new HashSet<>();
	
	public void adicionarContaBancaria(final ContaBancariaModelo contaBancaria) {
		this.contasBancarias.add(contaBancaria);
	}
	
	public void removerContaBancaria(final ContaBancariaModelo contaBancaria) {
		this.contasBancarias.remove(contaBancaria);
	}

}
