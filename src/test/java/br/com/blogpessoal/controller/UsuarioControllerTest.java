package br.com.blogpessoal.controller;

import br.com.blogpessoal.model.UsuarioModel;
import br.com.blogpessoal.repository.UsuarioRepository;
import br.com.blogpessoal.service.UsuarioService;
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
    void start(){
        usuarioRepository.deleteAll();
        usuarioService.cadastrarUsuario(new UsuarioModel(null,"Root", "root@root.com", "rootroot", "-"));
    }

    @Test
    @DisplayName("Cadastrar usuário")
    public void dcriarUsuario() {

        HttpEntity<UsuarioModel> corpoRequisicao = new HttpEntity<UsuarioModel>(new UsuarioModel(null, "Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "-"));

        ResponseEntity<UsuarioModel> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, UsuarioModel.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

    }

    @Test
    @DisplayName("Não permitir cadastros duplicados")
    public void naoPermitirCadastroUsuarioDuplicado() {

        usuarioService.cadastrarUsuario(new UsuarioModel(null,"Maria da Silva", "maria_silva@email.com.br", "13465278", "-"));

        HttpEntity<UsuarioModel> corpoRequisicao = new HttpEntity<UsuarioModel>(new UsuarioModel(null,"Maria da Silva", "maria_silva@email.com.br", "13465278", "-"));

        ResponseEntity<UsuarioModel> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, UsuarioModel.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar Usuário")
    public void atualizarUsuario() {

        Optional<UsuarioModel> usuarioCadastrado = usuarioService.cadastrarUsuario(new UsuarioModel(null,"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", "-"));

        UsuarioModel usuarioUpdate = new UsuarioModel(usuarioCadastrado.get().getId(),"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123" , "-");

        HttpEntity<UsuarioModel> corpoRequisicao = new HttpEntity<UsuarioModel>(usuarioUpdate);

        ResponseEntity<UsuarioModel> corpoResposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, UsuarioModel.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os Usuários")
    public void listarUsuarios() {

        usuarioService.cadastrarUsuario(new UsuarioModel(null,"Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123", "-"));

        usuarioService.cadastrarUsuario(new UsuarioModel(null,"Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123", "-"));

        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("root@root.com", "rootroot")
                .exchange("/usuarios/all", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

}