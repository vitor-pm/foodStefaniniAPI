package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Loja;
import br.com.stefaninifood.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {
    @Autowired
    private LojaService service;

    @GetMapping()
    public ResponseEntity<List<?>> getAll(@RequestParam(required = false) String nome) {
        return service.getAllLojas(nome);
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
