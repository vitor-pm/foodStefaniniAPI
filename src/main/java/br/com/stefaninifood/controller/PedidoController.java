package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Pedido;
import br.com.stefaninifood.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping()
    public ResponseEntity<List<Pedido>> getAll(@RequestParam(required = false) String cliente) {
        return service.getAllPedidos(cliente);
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
