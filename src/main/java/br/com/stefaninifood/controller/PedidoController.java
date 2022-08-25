package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Pedido;
import br.com.stefaninifood.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping()
    @Cacheable(value = "listaPedidos")
    public ResponseEntity<Page<Pedido>> getAll(@RequestParam(required = false) String cliente,
                                               Pageable paginacao) {
        return service.getAllPedidos(cliente, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable int id) {
        return service.getPedidoById(id);
    }

    @PostMapping
    @CacheEvict(value = "listaPedidos", allEntries = true)
    public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido) {
        return service.insertPedido(pedido);
    }

    @PutMapping
    @CacheEvict(value = "listaPedidos", allEntries = true)
    public ResponseEntity<Pedido> putPedido(@RequestBody @Valid Pedido pedido) {
        return service.updatePedido(pedido);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "listaPedidos", allEntries = true)
    public ResponseEntity<?> deletePedido(@PathVariable int id) {
        return service.deletePedido(id);
    }
}
