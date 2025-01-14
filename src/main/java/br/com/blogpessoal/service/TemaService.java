package br.com.blogpessoal.service;

import br.com.blogpessoal.dto.TemaUpdateDto;
import br.com.blogpessoal.model.TemaModel;
import br.com.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    @ResponseStatus(HttpStatus.CREATED)
    public void create(TemaModel temaModel){
        temaRepository.save(temaModel);
    }

    @ResponseStatus(HttpStatus.OK)
    public void update (TemaUpdateDto temaUpdateDto){
        Optional<TemaModel> temaOpt = temaRepository.findById(temaUpdateDto.id());
        if(temaOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "o id informado não existe: " + temaUpdateDto.id());
        }
        TemaModel temaModel = new TemaModel(temaUpdateDto.id(), temaUpdateDto.descricao());
        temaRepository.save(temaModel);
    }

    @ResponseStatus(HttpStatus.OK)
    public void delete(Long id){
        Optional<TemaModel> temaOpt = temaRepository.findById(id);
        if(temaOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O id do registro informado não existe: " + id);
        }
        temaRepository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TemaModel> findById(Long id){
        return temaRepository.findById(id)
                .map(tema -> ResponseEntity.status(HttpStatus.OK).body(tema))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe um registro com o id " + id));
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TemaModel>> findAllByDescricaoContainingIgnoreCase(String descricao){
        return ResponseEntity.status(HttpStatus.OK).body(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TemaModel>> findAll(){
        if(temaRepository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum registro encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(temaRepository.findAll());
    }

}
