package com.example.stock_control_api.repository;

import com.example.stock_control_api.model.PedidoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompraRepository extends JpaRepository<PedidoCompra, Long> {

}
