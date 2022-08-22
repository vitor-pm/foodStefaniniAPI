package br.com.stefaninifood.controller;

import br.com.stefaninifood.model.Pedido;
import br.com.stefaninifood.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @GetMapping()
    public ResponseEntity<List<Pedido>> getAll(@RequestParam(required = false) String cliente){
        if (cliente == null) {
            return  ResponseEntity.ok(repository.findAll());
        }
        return  ResponseEntity.ok(repository.findByCliente_Nome(cliente));
    }

    @PostMapping
    public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pedido));
    }

    @PutMapping
    public  ResponseEntity<Pedido> putPedido (@RequestBody @Valid Pedido pedido){
        Optional<Pedido> pedidoBanco = repository.findById(pedido.getId());
        if (pedidoBanco.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(pedido));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePedido(@PathVariable int id){
        Optional<Pedido> pedido = repository.findById(id);
        if (pedido.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
