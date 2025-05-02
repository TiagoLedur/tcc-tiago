package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.itempedido.ItemPedidoCompraRequestDTO;
import com.example.stock_control_api.dto.itempedido.ItemPedidoCompraResponseDTO;
import com.example.stock_control_api.service.ItemPedidoCompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido-compra")
@RequiredArgsConstructor
public class ItemPedidoCompraController {

    private final ItemPedidoCompraService itemPedidoCompraService;

    @GetMapping
    public ResponseEntity<List<ItemPedidoCompraResponseDTO>> list() {
        return ResponseEntity.ok(itemPedidoCompraService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoCompraResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemPedidoCompraService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ItemPedidoCompraResponseDTO> save(@Valid @RequestBody ItemPedidoCompraRequestDTO dto) {
        return ResponseEntity.ok(itemPedidoCompraService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemPedidoCompraService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
