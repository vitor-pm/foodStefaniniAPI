package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping()
    public ResponseEntity<Page<Cliente>> getAll(@RequestParam(required = false) String nome,
                                                Pageable paginacao) {
        System.out.println(paginacao);
        return service.getAllClientes(nome, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id) {
        return service.getClienteById(id);
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
