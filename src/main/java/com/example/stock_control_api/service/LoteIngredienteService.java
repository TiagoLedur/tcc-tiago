package com.example.stock_control_api.service;

import com.example.stock_control_api.model.LoteIngrediente;
import com.example.stock_control_api.repository.LoteIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteIngredienteService {

    private final LoteIngredienteRepository loteIngredienteRepository;

    public LoteIngredienteService(LoteIngredienteRepository loteIngredienteRepository) {
        this.loteIngredienteRepository = loteIngredienteRepository;
    }

    public List<LoteIngrediente> listar() {
        return loteIngredienteRepository.findAll();
    }

    public Optional<LoteIngrediente> buscarPorId(Long id) {
        return loteIngredienteRepository.findById(id);
    }

    public LoteIngrediente salvar(LoteIngrediente categoria) {
        return loteIngredienteRepository.save(categoria);
    }

    public void deletar(Long id) {
        loteIngredienteRepository.deleteById(id);
    }

}

