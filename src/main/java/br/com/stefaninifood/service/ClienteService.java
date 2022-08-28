package br.com.stefaninifood.service;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity<Page<Cliente>> getAllClientes(String nome, Pageable paginacao) {
        if (nome == null) {
            return ResponseEntity.ok(repository.findAll(paginacao));
        }
        return ResponseEntity.ok(repository.findByNomeContaining(nome, paginacao));
    }

    public ResponseEntity<Cliente> getClienteById(int id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Cliente> insertCliente(Cliente cliente) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
    }

    public ResponseEntity<Cliente> updateCliente(Cliente cliente) {
        Optional<Cliente> clienteBanco = repository.findById(cliente.getId());
        if (clienteBanco.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(cliente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<?> deleteCliente(int id) {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente " + id + " excluído.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente " + id + " não encontrado.");
        }
    }
}
