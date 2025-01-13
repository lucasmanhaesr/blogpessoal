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
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository repository;

    @ResponseStatus(HttpStatus.CREATED)
    public void create(PostagemModel postagem) {
        repository.save(postagem);
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostagemModel> update(long id, PostagemModel postagemModel) {
        Optional<PostagemModel> postagemOpt = repository.findById(id);
        if (postagemOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe registro com o id" + id);
        }
        postagemModel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagemModel));
    }

    @ResponseStatus(HttpStatus.OK)
    public void delete(long id) {
        Optional<PostagemModel> postagemOpt = repository.findById(id);
        if (postagemOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro com o id" + id);
        }
        repository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostagemModel> findByID(long id) {
        return repository.findById(id)
                .map(postagem -> ResponseEntity.status(HttpStatus.OK).body(postagem))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado o registro com id: " + id));
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PostagemModel>> findByTituloContainingIgnoreCase(String titulo) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PostagemModel>> findAll(){
        if(repository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe registros na base de dados");
        }
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

}
