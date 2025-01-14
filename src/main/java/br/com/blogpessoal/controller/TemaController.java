package br.com.blogpessoal.controller;

import br.com.blogpessoal.dto.TemaUpdateDto;
import br.com.blogpessoal.model.TemaModel;
import br.com.blogpessoal.service.TemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody TemaModel temaModel) {
        temaService.create(temaModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("")
    public void update(@Valid @RequestBody TemaUpdateDto temaUpdateDto) {
        temaService.update(temaUpdateDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") long id){
        temaService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "", params = "id")
    public ResponseEntity<TemaModel> findById(@RequestParam("id") long id) {
        return temaService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "", params = "descricao")
    public ResponseEntity<List<TemaModel>> findAllByDescricaoContainingIgnoreCase(@RequestParam("descricao") String descricao) {
        return temaService.findAllByDescricaoContainingIgnoreCase(descricao);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<List<TemaModel>> findAll() {
        return temaService.findAll();
    }
}
