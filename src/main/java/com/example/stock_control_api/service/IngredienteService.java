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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
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
        ingrediente.setQuantidadeMinima(dto.getQuantidadeMinima());
        ingrediente.setQuantidadeTotal(dto.getQuantidadeTotal());

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
        existente.setValidade(dto.getValidade());
        existente.setCategoria(categoria);
        existente.setQuantidadeMinima(dto.getQuantidadeMinima());

        return IngredienteMapper.toDTO(ingredienteRepository.save(existente));
    }

    public void deletarIngrediente(Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Ingrediente não encontrado");
        }
        ingredienteRepository.deleteById(id);
    }

    public List<IngredienteResponseDTO> filtrarIngredientes(String nome,
                                                            Long categoriaId,
                                                            LocalDate dataInicio,
                                                            LocalDate dataFim,
                                                            String sortBy,
                                                            Sort.Direction direction) {

        List<Ingrediente> ingredientes = ingredienteRepository.findAll();

        if (nome != null && !nome.trim().isEmpty()) {
            String termo = nome.toLowerCase();
            ingredientes = ingredientes.stream()
                    .filter(i -> i.getNome() != null && i.getNome().toLowerCase().contains(termo))
                    .collect(Collectors.toList());
        }

        if (categoriaId != null) {
            ingredientes = ingredientes.stream()
                    .filter(i -> i.getCategoria() != null && i.getCategoria().getId().equals(categoriaId))
                    .collect(Collectors.toList());
        }

        if (dataInicio != null) {
            ingredientes = ingredientes.stream()
                    .filter(i -> i.getValidade() != null && !i.getValidade().isBefore(dataInicio))
                    .collect(Collectors.toList());
        }

        if (dataFim != null) {
            ingredientes = ingredientes.stream()
                    .filter(i -> i.getValidade() != null && !i.getValidade().isAfter(dataFim))
                    .collect(Collectors.toList());
        }

        if (sortBy != null && direction != null) {
            Comparator<Ingrediente> comparator = null;
            switch (sortBy) {
                case "quantidade":
                    comparator = Comparator.comparing(Ingrediente::getQuantidadeTotal);
                    break;
                case "preco":
                    comparator = Comparator.comparing(Ingrediente::getPrecoUnitario);
                    break;
                case "validade":
                    comparator = Comparator.comparing(Ingrediente::getValidade);
                    break;
            }

            if (comparator != null) {
                if (direction.isDescending()) {
                    comparator = comparator.reversed();
                }
                ingredientes = ingredientes.stream()
                        .sorted(comparator)
                        .collect(Collectors.toList());
            }
        }

        return ingredientes.stream()
                .map(IngredienteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
