package com.example.stock_control_api.repository;


import com.example.stock_control_api.model.ItensSaida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensSaidaRepository extends JpaRepository<ItensSaida, Long> {

}
