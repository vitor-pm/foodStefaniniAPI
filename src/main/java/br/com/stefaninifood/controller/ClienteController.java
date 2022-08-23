package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping()
    public ResponseEntity<List<Cliente>> getAll(@RequestParam(required = false) String nome) {
        return service.getAllClientes(nome);
    }

    @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody @Valid Cliente cliente) {
        return service.insertCliente(cliente);
    }

    @PutMapping
    public ResponseEntity<Cliente> putCliente(@RequestBody @Valid Cliente cliente) {
        return service.updateCliente(cliente);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCliente(@PathVariable int id) {
        return service.deleteCliente(id);
    }
}
