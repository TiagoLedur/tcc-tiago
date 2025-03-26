package com.example.stock_control_api.service;

import com.example.stock_control_api.model.LoteIngrediente;
import com.example.stock_control_api.repository.LoteIngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoteIngredienteService {

    private final LoteIngredienteRepository loteIngredienteRepository;

    public List<LoteIngrediente> list() {
        return loteIngredienteRepository.findAll();
    }

    public Optional<LoteIngrediente> findById(Long id) {
        return loteIngredienteRepository.findById(id);
    }

    public LoteIngrediente save(LoteIngrediente categoria) {
        return loteIngredienteRepository.save(categoria);
    }

    public void delete(Long id) {
        loteIngredienteRepository.deleteById(id);
    }

}

