package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Loja;
import br.com.stefaninifood.model.dto.LojaDto;
import br.com.stefaninifood.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private LojaRepository repository;

    @GetMapping()
    public ResponseEntity<List<?>> getAll(@RequestParam(required = false) String nome){
        List<LojaDto> lojas = new ArrayList<>();

        if (nome == null) {
            List<Loja> LojasBanco = repository.findAll();
            LojasBanco.forEach(l -> lojas.add(new LojaDto(l)));

            return ResponseEntity.ok(lojas);
        }
        List<Loja> LojasBanco = repository.findByNomeContaining(nome);
        LojasBanco.forEach(l -> lojas.add(new LojaDto(l)));

        return ResponseEntity.ok(repository.findByNomeContaining(nome));
    }

    @PostMapping
    public ResponseEntity<Loja> postLoja (@RequestBody Loja loja) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(loja));
    }

    @PutMapping
    public  ResponseEntity<Loja> putLoja (@RequestBody @Valid Loja loja){
        Optional<Loja> lojaBanco = repository.findById(loja.getId());
        if (lojaBanco.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(loja));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLoja (@PathVariable int id){
        Optional<Loja> loja = repository.findById(id);
        if (loja.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
