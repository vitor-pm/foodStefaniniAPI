package br.com.stefaninifood.service;

import br.com.stefaninifood.model.Produto;
import br.com.stefaninifood.model.dto.ProdutoDetalharDTO;
import br.com.stefaninifood.model.dto.ProdutoDto;
import br.com.stefaninifood.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ResponseEntity<Page<?>> getAllProdutos(String nome, Pageable paginacao) {
        if (nome == null) {
            Page<Produto> produtosBanco = repository.findAll(paginacao);

            return ResponseEntity.ok(ProdutoDto.converter(produtosBanco));
        }

        return ResponseEntity.ok(repository.findByNomeContaining(nome, paginacao));
    }

    public ResponseEntity<ProdutoDetalharDTO> getProdutoById(int id) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isPresent()) {
            ProdutoDetalharDTO detalhar = new ProdutoDetalharDTO(produto.get());
            return ResponseEntity.ok().body(detalhar);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
