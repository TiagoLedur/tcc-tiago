package com.example.stock_control_api.controller;

import com.example.stock_control_api.model.PedidoCompra;
import com.example.stock_control_api.service.PedidoCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoCompraController {

    private final PedidoCompraService pedidoCompraService;

    @GetMapping
    public List<PedidoCompra> listAll() {
        return pedidoCompraService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PedidoCompra>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoCompraService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoCompra> create(@RequestBody PedidoCompra pedidoCompra) {
        PedidoCompra novoPedido = pedidoCompraService.save(pedidoCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoCompra> update(@PathVariable Long id, @RequestBody PedidoCompra pedidoCompra) {
        pedidoCompra.setId(id);
        return ResponseEntity.ok(pedidoCompraService.save(pedidoCompra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoCompraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
