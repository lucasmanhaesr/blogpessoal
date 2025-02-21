package br.com.blogpessoal.service;

import br.com.blogpessoal.dto.PostagemUpdateDto;
import br.com.blogpessoal.model.PostagemModel;
import br.com.blogpessoal.repository.PostagemRepository;
import br.com.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired
    private PostagemRepository repository;
    @Autowired
    private TemaRepository temaRepository;

    @ResponseStatus(HttpStatus.CREATED)
    public void create(PostagemModel postagem) {
        if(temaRepository.existsById(postagem.getTema().getId())) {
            postagem.setData(LocalDateTime.now());
            repository.save(postagem);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe o id do tema informado: " + postagem.getTema().getId());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostagemModel> update(PostagemUpdateDto postagemUpdateDto) {
        if(temaRepository.existsById(postagemUpdateDto.tema().getId())) {
            Optional<PostagemModel> postagemOpt = repository.findById(postagemUpdateDto.id());
            if (postagemOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registro com o id: " + postagemUpdateDto.id());
            }
            PostagemModel postagemModel = new PostagemModel(postagemUpdateDto.id(), postagemUpdateDto.titulo(), postagemUpdateDto.texto(), postagemUpdateDto.data(), postagemUpdateDto.tema());
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagemModel));
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe o id do tema informado: " + postagemUpdateDto.tema().getId());
        }
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe registros na base de dados");
        }
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

}
