package com.example.stock_control_api.service;

import com.example.stock_control_api.model.Categoria;
import com.example.stock_control_api.model.Ingrediente;

import com.example.stock_control_api.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<Ingrediente> listar() {
        return ingredienteRepository.findAll();
    }

    public Optional<Ingrediente> buscarPorId(Long id) {
        return ingredienteRepository.findById(id);
    }

    public Ingrediente salvar(Ingrediente categoria) {
        return ingredienteRepository.save(categoria);
    }

    public void deletar(Long id) {
        ingredienteRepository.deleteById(id);
    }

}
