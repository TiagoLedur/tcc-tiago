package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.MovimentacaoRequestDTO;
import com.example.stock_control_api.dto.MovimentacaoResponseDTO;
import com.example.stock_control_api.mapper.MovimentacaoMapper;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.Movimentacao;
import com.example.stock_control_api.repository.IngredienteRepository;
import com.example.stock_control_api.repository.MovimentacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final IngredienteRepository ingredienteRepository;

    public MovimentacaoResponseDTO create(MovimentacaoRequestDTO dto) {
        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));

        Movimentacao movimentacao = MovimentacaoMapper.toEntity(dto, ingrediente);
        movimentacaoRepository.save(movimentacao);

        return MovimentacaoMapper.toDTO(movimentacao);
    }

    public List<MovimentacaoResponseDTO> list() {
        return movimentacaoRepository.findAll()
                .stream()
                .map(MovimentacaoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
