package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.lote.*;
import com.example.stock_control_api.mapper.LoteIngredienteMapper;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.LoteIngrediente;
import com.example.stock_control_api.repository.IngredienteRepository;
import com.example.stock_control_api.repository.LoteIngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoteIngredienteService {

    private final LoteIngredienteRepository loteIngredienteRepository;
    private final IngredienteRepository ingredienteRepository;

    public List<LoteIngredienteResponseDTO> list() {
        return loteIngredienteRepository.findAll()
                .stream()
                .map(LoteIngredienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LoteIngredienteResponseDTO findById(Long id) {
        return loteIngredienteRepository.findById(id)
                .map(LoteIngredienteMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException("Lote não encontrado"));
    }

    public LoteIngredienteResponseDTO save(LoteIngredienteRequestDTO dto) {
        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new NoSuchElementException("Ingrediente não encontrado"));

        LoteIngrediente entity = LoteIngredienteMapper.toEntity(dto, ingrediente);
        return LoteIngredienteMapper.toDTO(loteIngredienteRepository.save(entity));
    }

    public void delete(Long id) {
        loteIngredienteRepository.deleteById(id);
    }
}
