package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Produto;
import br.com.stefaninifood.model.dto.ProdutoDetalharDTO;
import br.com.stefaninifood.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping()
    public ResponseEntity<Page<?>> getAll(@RequestParam(required = false) String nome,
                                          Pageable paginacao) {
        return service.getAllProdutos(nome, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<ProdutoDetalharDTO> getById(@PathVariable int id) {
        return service.getProdutoById(id);
    }

    @PostMapping
    public ResponseEntity<Produto> postProduto(@RequestBody @Valid Produto produto) {
        return service.insertProduto(produto);
    }

    @PutMapping
    public ResponseEntity<Produto> putProduto(@RequestBody @Valid Produto produto) {
        return service.updateProduto(produto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduto(@PathVariable int id) {
        return service.deleteProduto(id);
    }

}
