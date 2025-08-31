package com.example.stock_control_api.repository;

import com.example.stock_control_api.model.ItensSaida;
import com.example.stock_control_api.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long> {
}
