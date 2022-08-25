package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Loja;
import br.com.stefaninifood.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/loja")
public class LojaController {
    @Autowired
    private LojaService service;

    @GetMapping()
    public ResponseEntity<Page<?>> getAll(@RequestParam(required = false) String nome,
                                          Pageable paginacao) {
        return service.getAllLojas(nome, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<Loja> getById(@PathVariable int id) {
        return service.getLojaById(id);
    }

    @PostMapping
    public ResponseEntity<Loja> postLoja(@RequestBody Loja loja) {
        return service.insertLoja(loja);
    }

    @PutMapping
    public ResponseEntity<Loja> putLoja(@RequestBody @Valid Loja loja) {
        return service.updateLoja(loja);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLoja(@PathVariable int id) {
        return service.deleteLoja(id);
    }

}
