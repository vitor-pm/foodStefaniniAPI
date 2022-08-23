package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Produto;
import br.com.stefaninifood.model.dto.ProdutoDto;
import br.com.stefaninifood.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping()
    public ResponseEntity<List<?>> getAll(@RequestParam(required = false) String nome) {
        List<ProdutoDto> produtos = new ArrayList<>();

        if (nome == null) {
            List<Produto> produtosBanco = repository.findAll();
            produtosBanco.forEach(p -> produtos.add(new ProdutoDto(p)));

            return ResponseEntity.ok(produtos);
        }

        repository.findByNomeContaining(nome).forEach(p -> produtos.add(new ProdutoDto(p)));

        return ResponseEntity.ok(repository.findByNomeContaining(nome));
    }

    @PostMapping
    public ResponseEntity<Produto> postProduto(@RequestBody @Valid Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> putProduto(@RequestBody @Valid Produto produto) {
        Optional<Produto> produtoBanco = repository.findById(produto.getId());
        if (produtoBanco.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduto(@PathVariable int id) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
