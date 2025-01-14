package br.com.blogpessoal.controller;

import br.com.blogpessoal.dto.PostagemUpdateDto;
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
    private PostagemService postagemService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody PostagemModel postagemModel) {
        postagemService.create(postagemModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("")
    public void update(@Valid @RequestBody PostagemUpdateDto postagemUpdateDto) {
        postagemService.update(postagemUpdateDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        postagemService.delete(id);
    }

    @GetMapping(value = "", params = "id")
    public ResponseEntity<PostagemModel> findById(@RequestParam("id") Long id) {
        return postagemService.findByID(id);
    }

    @GetMapping(value = "", params = "titulo")
    public ResponseEntity<List<PostagemModel>> findByTitulo(@RequestParam("titulo") String titulo) {
        return postagemService.findByTituloContainingIgnoreCase(titulo);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<List<PostagemModel>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(postagemService.findAll().getBody());
    }

}
