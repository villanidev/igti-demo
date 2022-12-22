package br.com.villadev.igti;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.villadev.igti.persistencia.acessoadados.FuncionalidadeRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.InquilinoRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.OrganizacaoRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.PapelRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.PermissaoRepositorio;
import br.com.villadev.igti.persistencia.acessoadados.UsuarioRepositorio;
import br.com.villadev.igti.persistencia.modelos.FuncionalidadeModelo;
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
	InquilinoRepositorio inquilinoRepositorio;
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	PapelRepositorio papelRepositorio;
	
	@Autowired
	PermissaoRepositorio permissaoRepositorio;
	
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
		
		funcionalidadeRepositorio.save(funcionalidade1);
		organizacaoRepositorio.save(org1);
		
		System.out.println("FIM");
		
	}

}
