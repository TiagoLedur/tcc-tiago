package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.saida.SaidaRequestDTO;
import com.example.stock_control_api.dto.saida.SaidaResponseDTO;
import com.example.stock_control_api.mapper.SaidaMapper;
import com.example.stock_control_api.model.Saida;
import com.example.stock_control_api.model.Usuario;
import com.example.stock_control_api.repository.SaidaRepository;
import com.example.stock_control_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaidaService {

    private final SaidaRepository saidaRepository;
    private final UsuarioRepository usuarioRepository;

    public SaidaService(SaidaRepository saidaRepository, UsuarioRepository usuarioRepository) {
        this.saidaRepository = saidaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<SaidaResponseDTO> findAll() {
        return saidaRepository.findAll().stream()
                .map(SaidaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public SaidaResponseDTO findById(Long id) {
        Saida saida = saidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saída não encontrada"));
        return SaidaMapper.toResponseDTO(saida);
    }

    public SaidaResponseDTO save(SaidaRequestDTO dto) {
        Saida saida = SaidaMapper.toEntity(dto);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            saida.setUsuario(usuario);
        } else {
            saida.setUsuario(null);
        }

        saida = saidaRepository.save(saida);
        return SaidaMapper.toResponseDTO(saida);
    }

    public SaidaResponseDTO update(Long id, SaidaRequestDTO dto) {
        Saida saida = saidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saída não encontrada"));

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            saida.setUsuario(usuario);
        } else {
            saida.setUsuario(null);
        }

        saida = saidaRepository.save(saida);
        return SaidaMapper.toResponseDTO(saida);
    }

    public void delete(Long id) {
        if (!saidaRepository.existsById(id)) {
            throw new RuntimeException("Saída não encontrada");
        }
        saidaRepository.deleteById(id);
    }
}
