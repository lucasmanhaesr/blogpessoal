package br.com.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import br.com.blogpessoal.dto.UsuarioCadastroDto;
import br.com.blogpessoal.dto.UsuarioLoginDto;
import br.com.blogpessoal.model.UsuarioModel;
import br.com.blogpessoal.repository.UsuarioRepository;
import br.com.blogpessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
    public ResponseEntity <List<UsuarioModel>> getAll(){

        return ResponseEntity.ok(usuarioRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/logar")
    public ResponseEntity<UsuarioLoginDto> autenticarUsuario(@RequestBody Optional<UsuarioLoginDto> usuarioLoginDto){

        return usuarioService.autenticarUsuario(usuarioLoginDto)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioModel> postUsuario(@RequestBody @Valid UsuarioCadastroDto usuario) {

        return usuarioService.cadastrarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioModel> putUsuario(@Valid @RequestBody UsuarioModel usuario) {

        return usuarioService.atualizarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

}