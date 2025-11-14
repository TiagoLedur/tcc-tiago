package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.itensentrada.ItensEntradaRequestDTO;
import com.example.stock_control_api.dto.itensentrada.ItensEntradaResponseDTO;
import com.example.stock_control_api.mapper.ItensEntradaMapper;
import com.example.stock_control_api.model.Entrada;
import com.example.stock_control_api.model.ItensEntrada;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.repository.EntradaRepository;
import com.example.stock_control_api.repository.ItensEntradaRepository;
import com.example.stock_control_api.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItensEntradaService {

    private final ItensEntradaRepository itensEntradaRepository;
    private final EntradaRepository entradaRepository;
    private final IngredienteRepository ingredienteRepository;

    public ItensEntradaService(ItensEntradaRepository itensEntradaRepository,
                               EntradaRepository entradaRepository,
                               IngredienteRepository ingredienteRepository) {
        this.itensEntradaRepository = itensEntradaRepository;
        this.entradaRepository = entradaRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<ItensEntradaResponseDTO> findAll() {
        return itensEntradaRepository.findAll().stream()
                .map(ItensEntradaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ItensEntradaResponseDTO findById(Long id) {
        ItensEntrada item = itensEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de entrada não encontrado"));
        return ItensEntradaMapper.toResponseDTO(item);
    }

    public ItensEntradaResponseDTO save(ItensEntradaRequestDTO dto) {
        ItensEntrada item = ItensEntradaMapper.toEntity(dto);

        Entrada entrada = entradaRepository.findById(dto.getEntradaId())
                .orElseThrow(() -> new RuntimeException("Entrada não encontrada"));
        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        item.setEntrada(entrada);
        item.setIngrediente(ingrediente);

        item = itensEntradaRepository.save(item);

        BigDecimal atual = ingrediente.getQuantidadeTotal();
        BigDecimal soma = atual.add(dto.getQuantidade());
        ingrediente.setQuantidadeTotal(soma);
        ingredienteRepository.save(ingrediente);

        return ItensEntradaMapper.toResponseDTO(item);
    }

    public ItensEntradaResponseDTO update(Long id, ItensEntradaRequestDTO dto) {
        ItensEntrada item = itensEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de entrada não encontrado"));

        Entrada entrada = entradaRepository.findById(dto.getEntradaId())
                .orElseThrow(() -> new RuntimeException("Entrada não encontrada"));
        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        item.setEntrada(entrada);
        item.setIngrediente(ingrediente);
        item.setQuantidade(dto.getQuantidade());

        item = itensEntradaRepository.save(item);
        return ItensEntradaMapper.toResponseDTO(item);
    }

    public void delete(Long id) {
        if (!itensEntradaRepository.existsById(id)) {
            throw new RuntimeException("Item de entrada não encontrado");
        }
        itensEntradaRepository.deleteById(id);
    }
}
