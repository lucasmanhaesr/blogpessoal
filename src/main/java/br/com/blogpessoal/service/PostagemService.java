package br.com.blogpessoal.service;

import br.com.blogpessoal.model.PostagemModel;
import br.com.blogpessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository repository;

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PostagemModel>> listAll(){
        if(repository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe registros na base de dados");
        }
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

}
