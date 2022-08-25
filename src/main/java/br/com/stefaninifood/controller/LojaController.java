package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Loja;
import br.com.stefaninifood.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "listaLojas")
    public ResponseEntity<Page<?>> getAll(@RequestParam(required = false) String nome,
                                          Pageable paginacao) {
        return service.getAllLojas(nome, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<Loja> getById(@PathVariable int id) {
        return service.getLojaById(id);
    }

    @PostMapping
    @CacheEvict(value = "listaLojas", allEntries = true)
    public ResponseEntity<Loja> postLoja(@RequestBody Loja loja) {
        return service.insertLoja(loja);
    }

    @PutMapping
    @CacheEvict(value = "listaLojas", allEntries = true)
    public ResponseEntity<Loja> putLoja(@RequestBody @Valid Loja loja) {
        return service.updateLoja(loja);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaLojas", allEntries = true)
    public ResponseEntity<?> deleteLoja(@PathVariable int id) {
        return service.deleteLoja(id);
    }

}
