package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.relatorio.RelatorioRequestDTO;
import com.example.stock_control_api.dto.relatorio.RelatorioResponseDTO;
import com.example.stock_control_api.mapper.RelatorioMapper;
import com.example.stock_control_api.model.Relatorio;
import com.example.stock_control_api.model.Usuario;
import com.example.stock_control_api.repository.RelatorioRepository;
import com.example.stock_control_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final UsuarioRepository usuarioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository, UsuarioRepository usuarioRepository) {
        this.relatorioRepository = relatorioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RelatorioResponseDTO> findAll() {
        return relatorioRepository.findAll().stream()
                .map(RelatorioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RelatorioResponseDTO findById(Long id) {
        Relatorio rel = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));
        return RelatorioMapper.toResponseDTO(rel);
    }

    public RelatorioResponseDTO save(RelatorioRequestDTO dto) {
        Relatorio rel = RelatorioMapper.toEntity(dto);

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            rel.setUsuario(usuario);
        } else {
            rel.setUsuario(null);
        }

        rel = relatorioRepository.save(rel);
        return RelatorioMapper.toResponseDTO(rel);
    }

    public RelatorioResponseDTO update(Long id, RelatorioRequestDTO dto) {
        Relatorio rel = relatorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado"));

        rel.setTipo(dto.getTipo());

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            rel.setUsuario(usuario);
        } else {
            rel.setUsuario(null);
        }

        rel = relatorioRepository.save(rel);
        return RelatorioMapper.toResponseDTO(rel);
    }

    public void delete(Long id) {
        if (!relatorioRepository.existsById(id)) {
            throw new RuntimeException("Relatório não encontrado");
        }
        relatorioRepository.deleteById(id);
    }
}
