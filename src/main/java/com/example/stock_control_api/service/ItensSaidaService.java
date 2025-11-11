package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.itenssaida.ItensSaidaRequestDTO;
import com.example.stock_control_api.dto.itenssaida.ItensSaidaResponseDTO;
import com.example.stock_control_api.mapper.ItensSaidaMapper;
import com.example.stock_control_api.model.ItensSaida;
import com.example.stock_control_api.model.Saida;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.repository.ItensSaidaRepository;
import com.example.stock_control_api.repository.SaidaRepository;
import com.example.stock_control_api.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItensSaidaService {

    private final ItensSaidaRepository itensSaidaRepository;
    private final SaidaRepository saidaRepository;
    private final IngredienteRepository ingredienteRepository;

    public ItensSaidaService(ItensSaidaRepository itensSaidaRepository,
                             SaidaRepository saidaRepository,
                             IngredienteRepository ingredienteRepository) {
        this.itensSaidaRepository = itensSaidaRepository;
        this.saidaRepository = saidaRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<ItensSaidaResponseDTO> findAll() {
        return itensSaidaRepository.findAll().stream()
                .map(ItensSaidaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ItensSaidaResponseDTO findById(Long id) {
        ItensSaida item = itensSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de saída não encontrado"));
        return ItensSaidaMapper.toResponseDTO(item);
    }

    public ItensSaidaResponseDTO save(ItensSaidaRequestDTO dto) {
        ItensSaida item = ItensSaidaMapper.toEntity(dto);

        Saida s = saidaRepository.findById(dto.getSaidaId())
                .orElseThrow(() -> new RuntimeException("Saída não encontrada"));
        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        item.setSaida(s);
        item.setIngrediente(ingrediente);

        item = itensSaidaRepository.save(item);
        return ItensSaidaMapper.toResponseDTO(item);
    }

    public ItensSaidaResponseDTO update(Long id, ItensSaidaRequestDTO dto) {
        ItensSaida item = itensSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de saída não encontrado"));

        Saida s = saidaRepository.findById(dto.getSaidaId())
                .orElseThrow(() -> new RuntimeException("Saída não encontrada"));
        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        item.setSaida(s);
        item.setIngrediente(ingrediente);
        item.setQuantidade(dto.getQuantidade());

        item = itensSaidaRepository.save(item);
        return ItensSaidaMapper.toResponseDTO(item);
    }

    public void delete(Long id) {
        if (!itensSaidaRepository.existsById(id)) {
            throw new RuntimeException("Item de saída não encontrado");
        }
        itensSaidaRepository.deleteById(id);
    }
}
