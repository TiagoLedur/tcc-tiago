package com.example.stock_control_api.service;

import com.example.stock_control_api.model.ItemPedidoCompra;
import com.example.stock_control_api.repository.ItemPedidoCompraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoCompraService {

    private final ItemPedidoCompraRepository itemPedidoCompraRepository;

    public ItemPedidoCompraService(ItemPedidoCompraRepository itemPedidoCompraRepository) {
        this.itemPedidoCompraRepository = itemPedidoCompraRepository;
    }

    public List<ItemPedidoCompra> listar() {
        return itemPedidoCompraRepository.findAll();
    }

    public Optional<ItemPedidoCompra> buscarPorId(Long id) {
        return itemPedidoCompraRepository.findById(id);
    }

    public ItemPedidoCompra salvar(ItemPedidoCompra categoria) {
        return itemPedidoCompraRepository.save(categoria);
    }

    public void deletar(Long id) {
        itemPedidoCompraRepository.deleteById(id);
    }

}
