package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.ingrediente.IngredienteRequestDTO;
import com.example.stock_control_api.dto.ingrediente.IngredienteResponseDTO;
import com.example.stock_control_api.mapper.IngredienteMapper;
import com.example.stock_control_api.model.Categoria;
import com.example.stock_control_api.model.Ingrediente;

import com.example.stock_control_api.repository.CategoriaRepository;
import com.example.stock_control_api.repository.IngredienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final CategoriaRepository categoriaRepository;

    public IngredienteResponseDTO criarIngrediente(IngredienteRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        Ingrediente ingrediente = IngredienteMapper.toEntity(dto, categoria);
        ingrediente = ingredienteRepository.save(ingrediente);

        return IngredienteMapper.toDTO(ingrediente);
    }

    public List<IngredienteResponseDTO> listarTodos() {
        return ingredienteRepository.findAll()
                .stream()
                .map(IngredienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IngredienteResponseDTO buscarPorId(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));
        return IngredienteMapper.toDTO(ingrediente);
    }

    public IngredienteResponseDTO atualizarIngrediente(Long id, IngredienteRequestDTO dto) {
        Ingrediente existente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        existente.setNome(dto.getNome());
        existente.setUnidadeMedida(dto.getUnidadeMedida());
        existente.setPrecoUnitario(dto.getPreco());
        existente.setQuantidadeTotal(dto.getQuantidadeTotal());
        existente.setCategoria(categoria);

        return IngredienteMapper.toDTO(ingredienteRepository.save(existente));
    }

    public void deletarIngrediente(Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Ingrediente não encontrado");
        }
        ingredienteRepository.deleteById(id);
    }
}

