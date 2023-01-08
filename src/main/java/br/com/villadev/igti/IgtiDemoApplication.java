package br.com.villadev.igti;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.villadev.igti.persistencia.acessoadados.FornecedorRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.FuncionalidadeRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.OrganizacaoRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.PapelRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.PermissaoRepositorio;
import br.com.villadev.igti.persistencia.modelos.Banco;
import br.com.villadev.igti.persistencia.modelos.ContaBancariaModelo;
import br.com.villadev.igti.persistencia.modelos.ContatoModelo;
import br.com.villadev.igti.persistencia.modelos.EnderecoModelo;
import br.com.villadev.igti.persistencia.modelos.FornecedorModelo;
import br.com.villadev.igti.persistencia.modelos.FuncionalidadeModelo;
import br.com.villadev.igti.persistencia.modelos.InformacaoBancariaModelo;
import br.com.villadev.igti.persistencia.modelos.InquilinoModelo;
import br.com.villadev.igti.persistencia.modelos.LoginModelo;
import br.com.villadev.igti.persistencia.modelos.OrganizacaoModelo;
import br.com.villadev.igti.persistencia.modelos.PapelModelo;
import br.com.villadev.igti.persistencia.modelos.PermissaoModelo;
import br.com.villadev.igti.persistencia.modelos.UsuarioModelo;

@SpringBootApplication
public class IgtiDemoApplication implements CommandLineRunner {
	
	@Autowired
	FuncionalidadeRepositorio funcionalidadeRepositorio;
	
	@Autowired
	OrganizacaoRepositorio organizacaoRepositorio;
	
	@Autowired
	PapelRepositorio papelRepositorio;
	
	@Autowired
	PermissaoRepositorio permissaoRepositorio;
	
	@Autowired
	FornecedorRepositorio fornecedorRepositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(IgtiDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//cria organizacao
		OrganizacaoModelo org1 = OrganizacaoModelo.builder()
				.nome("Org1").build();
				
		//cria funcionalidade
		FuncionalidadeModelo funcionalidade1 = FuncionalidadeModelo.builder()
			.nome("funcionalidade1")
			.descricao("descricao funcionalidade").build();
		
		//liga funcionalidades com organizacao
		org1.adicionarFuncionalidade(funcionalidade1);
		
		//cria inquilino
		InquilinoModelo inquilino1 = InquilinoModelo.builder()
			.codigo(UUID.randomUUID().toString())
			.ipAcesso("192.168.0.5")
			.ultimoAcesso(LocalDateTime.now())
			.build();
		
		//liga inquilino com organizacao
		org1.setTenant(inquilino1);
		
		//cria login
		LoginModelo login1 = LoginModelo.builder().email("pedro@email.com").password("12345678").build();
		LoginModelo login2 = LoginModelo.builder().email("maria@email.com").password("12945678").build();
		LoginModelo login3 = LoginModelo.builder().email("jose@email.com").password("12349678").build();
		
		//cria usuarios e liga o login
		UsuarioModelo usuario1 = UsuarioModelo.builder().nome("Pedro da Silva").ativo(true)
			.login(login1).build();
		UsuarioModelo usuario2 = UsuarioModelo.builder().nome("Maria da Silva").ativo(true)
			.login(login2).build();
		UsuarioModelo usuario3 = UsuarioModelo.builder().nome("José da Silva").ativo(true)
			.login(login3).build();
		
		//liga inquilino com usuario
		inquilino1.adicionarUsuarios(List.of(usuario1, usuario2, usuario3));
		
		//cria papel
		PapelModelo papelBasico = PapelModelo.builder().nome("basico").descricao("permissoes basicas").build();
		PapelModelo papelAvancado = PapelModelo.builder().nome("avancado").descricao("permissoes avancadas").build();
		
		//cria permissao
		PermissaoModelo permissaoLeitura = PermissaoModelo.builder().nome("Leitura")
				.descricao("permite apenas visualizacao").build();
		PermissaoModelo permissaoEscrita = PermissaoModelo.builder().nome("Escrita")
				.descricao("permite escrita").build();
		
		//liga papel com permissao
		papelBasico.adicionarPermissao(permissaoLeitura);
		papelAvancado.adicionarPermissao(permissaoEscrita);
		
		//liga usuario com papel
		usuario1.adicionarPapel(papelBasico);
		usuario2.adicionarPapel(papelBasico);
		usuario3.adicionarPapel(papelAvancado);
		
		permissaoRepositorio.saveAll(List.of(permissaoEscrita, permissaoLeitura));
		
		papelRepositorio.saveAll(List.of(papelBasico, papelAvancado));
		
		funcionalidadeRepositorio.save(funcionalidade1);
		
		organizacaoRepositorio.save(org1);
		
		System.out.println("FIM dos modelos de estrutura");
		
		//cria informacao bancaria
		InformacaoBancariaModelo infoBancaria1 = InformacaoBancariaModelo.builder()
				.build();
		
		infoBancaria1.adicionarContaBancaria(
				ContaBancariaModelo.builder()
					.agencia("1545")
					.conta("45454545")
					.banco(Banco.BANCO_DO_BRASIL)
					.build());
		
		infoBancaria1.adicionarContaBancaria(
				ContaBancariaModelo.builder()
					.agencia("1578")
					.conta("98654545")
					.banco(Banco.CAIXA)
					.build());
		
		//cria endereco
		EnderecoModelo endereco1 = EnderecoModelo.builder()
				.logradouro("rua teste").numero(45).complemento("5º andar").cep("1856897848").build();
		
		EnderecoModelo endereco2 = EnderecoModelo.builder()
				.logradouro("rua teste2").numero(45).complemento("1º andar").cep("1850897848").build();
		
		//cria contato
		ContatoModelo contato1 = ContatoModelo.builder().email("teste1@email.com").telefone("5588989454515").build();
		ContatoModelo contato2 = ContatoModelo.builder().email("teste2@email.com").telefone("5588989454789").build();
		
		//cria fornecedor e associa informacao bancaria
		FornecedorModelo fornecedor1 = FornecedorModelo.builder().nomeFantasia("Fornec1").cnpj("48227518000199").codigo(UUID.randomUUID().toString())
				.informacaoBancaria(infoBancaria1)
				.build();
		
		//associa endereco ao fornecedor
		fornecedor1.adicionarEnderecos(List.of(endereco1, endereco2));
		
		//associa contato ao fornecedor
		fornecedor1.adicionarContatos(List.of(contato1, contato2));
		
		fornecedorRepositorio.save(fornecedor1);
		
		System.out.println("FIM dos modelos de negocio");
		
	}

}
