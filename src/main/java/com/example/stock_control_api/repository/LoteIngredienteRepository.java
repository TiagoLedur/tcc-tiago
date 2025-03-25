package com.example.stock_control_api.repository;

import com.example.stock_control_api.model.LoteIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteIngredienteRepository extends JpaRepository<LoteIngrediente, Long> {

}
