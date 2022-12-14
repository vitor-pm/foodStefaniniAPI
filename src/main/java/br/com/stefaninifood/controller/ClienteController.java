package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Cliente;
import br.com.stefaninifood.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "listaClientes")
    public ResponseEntity<Page<Cliente>> getAll(@RequestParam(required = false) String nome,
                                                Pageable paginacao) {
        return service.getAllClientes(nome, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable int id) {
        return service.getClienteById(id);
    }

    @PostMapping
    @CacheEvict(value = "listaClientes", allEntries = true)
    public ResponseEntity<Cliente> postCliente(@RequestBody @Valid Cliente cliente) {
        return service.insertCliente(cliente);
    }

    @PutMapping
    @CacheEvict(value = "listaClientes", allEntries = true)
    public ResponseEntity<Cliente> putCliente(@RequestBody @Valid Cliente cliente) {
        return service.updateCliente(cliente);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaClientes", allEntries = true)
    public ResponseEntity<?> deleteCliente(@PathVariable int id) {
        return service.deleteCliente(id);
    }
}
