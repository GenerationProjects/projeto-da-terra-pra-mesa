package com.generation.daterrapramesa.controllers;

import com.generation.daterrapramesa.model.Usuario;
import com.generation.daterrapramesa.model.UsuarioLogin;
import com.generation.daterrapramesa.repository.*;
import com.generation.daterrapramesa.service.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioService.cadastrarUsuario(new Usuario(0L, "Root", "root@root.com", "rootroot", ""));
	}

	@Test
	@DisplayName("Cadastrar um Usuário")
	public void deveCriarUmUsuario() {

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Felipe", "felipe.sousa@daterrapramesa.com.br", "12345678", "-"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

	}

	@Test
	@DisplayName("Não Deve permitir a duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Gabriela", "gabriela@daterrapramesa.com.br", "12345678", "-"));

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Gabriela", "gabriela@daterrapramesa.com.br", "12345678", "-"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);

		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());

	}

	@Test
	@DisplayName("Deve Atualizar os dados do Usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuario> usuarioCadastrado = usuarioService
				.cadastrarUsuario(new Usuario(0L, "Adriana", "adriana@daterrapramesa.com.br", "12345678", "-"));

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(usuarioCadastrado.get().getId(),
				"Adriana", "adriana@daterrapramesa.com.br", "12345678", "-"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

	}

	@Test
	@DisplayName("Deve Listar todos os Usuários")
	public void deveMostrarTodosUsuarios() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Wesley", "wesley@daterrapramesa.com.br", "12345678", "-"));

		usuarioService.cadastrarUsuario(new Usuario(0L, "Giovanni", "gyu@daterrapramesa.com.br", "12345678", "-"));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

	@Test
	@DisplayName("Listar Um Usuário Específico")
	public void deveListarApenasUmUsuario() {

		Optional<Usuario> usuarioBusca = usuarioService
				.cadastrarUsuario(new Usuario(0L, "Ana", "ana@daterrapramesa.com.br", "12345678", "-"));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/" + usuarioBusca.get().getId(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

	@Test
	@DisplayName("Login do Usuário")
	public void deveAutenticarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Marisa Souza", "marisa_souza@email.com.br", "13465278", "-"));

		HttpEntity<UsuarioLogin> corpoRequisicao = new HttpEntity<UsuarioLogin>(
				new UsuarioLogin(0L, "", "marisa_souza@email.com.br", "13465278", "", ""));

		ResponseEntity<UsuarioLogin> corpoResposta = testRestTemplate.exchange("/usuarios/logar", HttpMethod.POST,
				corpoRequisicao, UsuarioLogin.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

	}
}
