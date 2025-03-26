package com.example.stock_control_api.service;

import com.example.stock_control_api.model.Ingrediente;

import com.example.stock_control_api.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public List<Ingrediente> list() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> findById(Long id) {
        return ingredienteRepository.findById(id);
    }

    public Ingrediente save(Ingrediente categoria) {
        return ingredienteRepository.save(categoria);
    }

    public void delete(Long id) {
        ingredienteRepository.deleteById(id);
    }

}

