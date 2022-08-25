package br.com.stefaninifood.service;

import br.com.stefaninifood.model.Pedido;
import br.com.stefaninifood.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;


    public ResponseEntity<Page<Pedido>> getAllPedidos(String cliente, Pageable paginacao) {
        if (cliente == null) {
            return ResponseEntity.ok(repository.findAll(paginacao));
        }
        return ResponseEntity.ok(repository.findByCliente_Nome(cliente, paginacao));
    }

    public ResponseEntity<Pedido> getPedidoById(int id) {
        Optional<Pedido> pedido = repository.findById(id);
        return pedido.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Pedido> insertPedido(Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pedido));
    }

    public ResponseEntity<Pedido> updatePedido(Pedido pedido) {
        Optional<Pedido> pedidoBanco = repository.findById(pedido.getId());
        if (pedidoBanco.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(pedido));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> deletePedido(int id) {
        Optional<Pedido> pedido = repository.findById(id);
        if (pedido.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Pedido " + id + " excluído.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido " + id + " não encontrado.");
        }
    }
}




