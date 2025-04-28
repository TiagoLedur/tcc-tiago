package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.pedido.PedidoCompraRequestDTO;
import com.example.stock_control_api.dto.pedido.PedidoCompraResponseDTO;
import com.example.stock_control_api.mapper.PedidoCompraMapper;
import com.example.stock_control_api.model.PedidoCompra;
import com.example.stock_control_api.service.PedidoCompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos-compra")
@RequiredArgsConstructor
public class PedidoCompraController {

    private final PedidoCompraService pedidoCompraService;

    @PostMapping
    public PedidoCompraResponseDTO criarPedido(@RequestBody @Valid PedidoCompraRequestDTO pedidoDTO) {
        PedidoCompra pedido = pedidoCompraService.criarPedido(pedidoDTO);
        return PedidoCompraMapper.toDTO(pedido);
    }

    @GetMapping
    public List<PedidoCompraResponseDTO> listarPedidos() {
        return pedidoCompraService.listarPedidos()
                .stream()
                .map(PedidoCompraMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PedidoCompraResponseDTO buscarPorId(@PathVariable Long id) {
        PedidoCompra pedido = pedidoCompraService.buscarPorId(id);
        return PedidoCompraMapper.toDTO(pedido);
    }

    @PutMapping("/{id}/status")
    public PedidoCompraResponseDTO atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        PedidoCompra pedido = pedidoCompraService.atualizarStatus(id, status);
        return PedidoCompraMapper.toDTO(pedido);
    }
}
