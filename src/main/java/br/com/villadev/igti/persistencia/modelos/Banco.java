package br.com.villadev.igti.persistencia.modelos;

public enum Banco {
	
	ITAU("Itau", 341),
	BANCO_DO_BRASIL("Banco do Brasil", 001),
	CAIXA("Caixa Economica Federal", 104),
	SANTANDER("Santander", 033),
	BRADESCO("Bradesco", 237);
	
	private String nome;
	private Integer codigo;
	
	Banco(String nome, Integer codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
}
