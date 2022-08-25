package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Pedido;
import br.com.stefaninifood.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Page<Pedido>> getAll(@RequestParam(required = false) String cliente,
                                               Pageable paginacao) {
        return service.getAllPedidos(cliente, paginacao);
    }

    @GetMapping("/detalhar/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable int id) {
        return service.getPedidoById(id);
    }

    @PostMapping
    public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido) {
        return service.insertPedido(pedido);
    }

    @PutMapping
    public ResponseEntity<Pedido> putPedido(@RequestBody @Valid Pedido pedido) {
        return service.updatePedido(pedido);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePedido(@PathVariable int id) {
        return service.deletePedido(id);
    }
}
