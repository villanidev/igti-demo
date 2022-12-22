package br.com.villadev.igti.persistencia.modelos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tbl_informacao_bancaria")
public class InformacaoBancariaModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String agencia;
	private String conta;
	@ElementCollection(targetClass = Banco.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "tbl_banco", 
		joinColumns = @JoinColumn(name = "informacao_bancaria_id")
	)
	@Enumerated(EnumType.STRING)
	@Column(name = "banco")
	@Builder.Default
	private Set<Banco> contasBancarias = new HashSet<>();

}
