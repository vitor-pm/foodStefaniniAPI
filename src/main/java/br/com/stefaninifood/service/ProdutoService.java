package br.com.stefaninifood.service;

import br.com.stefaninifood.model.Produto;
import br.com.stefaninifood.model.dto.ProdutoDto;
import br.com.stefaninifood.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ResponseEntity<List<?>> getAllProdutos(String nome) {
        List<ProdutoDto> produtos = new ArrayList<>();

        if (nome == null) {
            List<Produto> produtosBanco = repository.findAll();
            produtosBanco.forEach(p -> produtos.add(new ProdutoDto(p)));

            return ResponseEntity.ok(produtos);
        }

        repository.findByNomeContaining(nome).forEach(p -> produtos.add(new ProdutoDto(p)));

        return ResponseEntity.ok(repository.findByNomeContaining(nome));
    }

    public ResponseEntity<Produto> insertProduto(Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
    }

    public ResponseEntity<Produto> updateProduto(Produto produto) {
        Optional<Produto> produtoBanco = repository.findById(produto.getId());
        if (produtoBanco.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> deleteProduto(int id) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Produto " + id + " excluído.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto " + id + " não encontrado.");
        }
    }
}
