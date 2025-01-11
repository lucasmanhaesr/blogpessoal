package br.com.blogpessoal.controller;

import br.com.blogpessoal.model.PostagemModel;
import br.com.blogpessoal.service.PostagemService;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    public ResponseEntity<List<PostagemModel>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll().getBody());
    }

}
