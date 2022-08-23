package br.com.stefaninifood.service;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ResponseEntity<List<Cliente>> getAllClientes(String nome) {
        if (nome == null) {
            return ResponseEntity.ok(repository.findAll());
        }
        return ResponseEntity.ok(repository.findByNomeContaining(nome));
    }

    public ResponseEntity<Cliente> insertCliente(Cliente cliente) {
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
