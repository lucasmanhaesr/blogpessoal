package br.com.blogpessoal.controller;

import br.com.blogpessoal.model.PostagemModel;
import br.com.blogpessoal.service.PostagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

    @Autowired
    private PostagemService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody PostagemModel postagemModel) {
        service.create(postagemModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<PostagemModel> update(@Valid @PathVariable long id, @RequestBody PostagemModel postagemModel) {
        return service.update(id, postagemModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @GetMapping(value = "", params = "id")
    public ResponseEntity<PostagemModel> findById(@RequestParam("id") Long id) {
        return service.findByID(id);
    }

    @GetMapping(value = "", params = "titulo")
    public ResponseEntity<List<PostagemModel>> findByTitulo(@RequestParam("titulo") String titulo) {
        return service.findByTituloContainingIgnoreCase(titulo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<List<PostagemModel>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll().getBody());
    }



}
