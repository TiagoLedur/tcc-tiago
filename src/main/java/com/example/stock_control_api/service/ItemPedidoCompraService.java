package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.itempedido.ItemPedidoCompraRequestDTO;
import com.example.stock_control_api.dto.itempedido.ItemPedidoCompraResponseDTO;
import com.example.stock_control_api.mapper.ItemPedidoCompraMapper;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.ItemPedidoCompra;
import com.example.stock_control_api.model.PedidoCompra;
import com.example.stock_control_api.repository.IngredienteRepository;
import com.example.stock_control_api.repository.ItemPedidoCompraRepository;
import com.example.stock_control_api.repository.PedidoCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemPedidoCompraService {

    private final ItemPedidoCompraRepository itemPedidoCompraRepository;
    private final IngredienteRepository ingredienteRepository;
    private final PedidoCompraRepository pedidoCompraRepository;

    public List<ItemPedidoCompraResponseDTO> list() {
        return itemPedidoCompraRepository.findAll()
                .stream()
                .map(ItemPedidoCompraMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemPedidoCompraResponseDTO findById(Long id) {
        return itemPedidoCompraRepository.findById(id)
                .map(ItemPedidoCompraMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("ItemPedidoCompra não encontrado com ID: " + id));
    }

    public ItemPedidoCompraResponseDTO save(ItemPedidoCompraRequestDTO dto) {
        PedidoCompra pedido = pedidoCompraRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new NoSuchElementException("PedidoCompra não encontrado com ID: " + dto.getPedidoId()));

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new NoSuchElementException("Ingrediente não encontrado com ID: " + dto.getIngredienteId()));

        ItemPedidoCompra entity = ItemPedidoCompraMapper.toEntity(dto, pedido, ingrediente);
        return ItemPedidoCompraMapper.toDTO(itemPedidoCompraRepository.save(entity));
    }

    public void delete(Long id) {
        itemPedidoCompraRepository.deleteById(id);
    }
}
