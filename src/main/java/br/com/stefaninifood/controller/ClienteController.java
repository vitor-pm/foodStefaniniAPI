package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAll(@RequestParam(required = false) String nome) {
        if (nome == null) {
            return ResponseEntity.ok(repository.findAll());
        }
        return ResponseEntity.ok(repository.findByNomeContaining(nome));
    }

    @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody @Valid Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
    }

    @PutMapping
    public ResponseEntity<Cliente> putCliente(@RequestBody @Valid Cliente cliente) {
        Optional<Cliente> clienteBanco = repository.findById(cliente.getId());
        if (clienteBanco.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(cliente));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCliente(@PathVariable int id) {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
