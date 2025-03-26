package com.example.stock_control_api.controller;

import com.example.stock_control_api.model.ItemPedidoCompra;
import com.example.stock_control_api.service.ItemPedidoCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens-pedido")
@RequiredArgsConstructor
public class ItemPedidoCompraController {

    private final ItemPedidoCompraService itemPedidoCompraService;

    @GetMapping
    public List<ItemPedidoCompra> listAll() {
        return itemPedidoCompraService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ItemPedidoCompra>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemPedidoCompraService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ItemPedidoCompra> create(@RequestBody ItemPedidoCompra itemPedidoCompra) {
        ItemPedidoCompra novoItem = itemPedidoCompraService.save(itemPedidoCompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoCompra> update(@PathVariable Long id, @RequestBody ItemPedidoCompra itemPedidoCompra) {
        itemPedidoCompra.setId(id);
        return ResponseEntity.ok(itemPedidoCompraService.save(itemPedidoCompra));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemPedidoCompraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
