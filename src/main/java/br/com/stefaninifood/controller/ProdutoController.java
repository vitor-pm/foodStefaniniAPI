package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Produto;
import br.com.stefaninifood.model.dto.ProdutoDetalharDTO;
import br.com.stefaninifood.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "listaProdutos")
    public ResponseEntity<Page<?>> getAll(@RequestParam(required = false) String nome,
                                          Pageable paginacao) {
        return service.getAllProdutos(nome, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<ProdutoDetalharDTO> getById(@PathVariable int id) {
        return service.getProdutoById(id);
    }

    @PostMapping
    @CacheEvict(value = "listaProdutos", allEntries = true)
    public ResponseEntity<Produto> postProduto(@RequestBody @Valid Produto produto) {
        return service.insertProduto(produto);
    }

    @PutMapping
    @CacheEvict(value = "listaProdutos", allEntries = true)
    public ResponseEntity<Produto> putProduto(@RequestBody @Valid Produto produto) {
        return service.updateProduto(produto);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaProdutos", allEntries = true)
    public ResponseEntity<?> deleteProduto(@PathVariable int id) {
        return service.deleteProduto(id);
    }

}
