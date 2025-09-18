package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.entrada.EntradaRequestDTO;
import com.example.stock_control_api.dto.entrada.EntradaResponseDTO;
import com.example.stock_control_api.mapper.EntradaMapper;
import com.example.stock_control_api.model.Entrada;
import com.example.stock_control_api.model.Fornecedor;
import com.example.stock_control_api.model.Usuario;
import com.example.stock_control_api.repository.EntradaRepository;
import com.example.stock_control_api.repository.FornecedorRepository;
import com.example.stock_control_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final UsuarioRepository usuarioRepository;

    public EntradaService(EntradaRepository entradaRepository,
                          FornecedorRepository fornecedorRepository,
                          UsuarioRepository usuarioRepository) {
        this.entradaRepository = entradaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<EntradaResponseDTO> findAll() {
        return entradaRepository.findAll().stream()
                .map(EntradaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public EntradaResponseDTO findById(Long id) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada não encontrada"));
        return EntradaMapper.toResponseDTO(entrada);
    }

    public EntradaResponseDTO save(EntradaRequestDTO dto) {
        Entrada entrada = EntradaMapper.toEntity(dto);

        if (dto.getFornecedorId() != null) {
            Fornecedor f = fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
            entrada.setFornecedor(f);
        } else {
            entrada.setFornecedor(null);
        }

        if (dto.getUsuarioId() != null) {
            Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            entrada.setUsuario(u);
        } else {
            entrada.setUsuario(null);
        }

        entrada = entradaRepository.save(entrada);
        return EntradaMapper.toResponseDTO(entrada);
    }

    public EntradaResponseDTO update(Long id, EntradaRequestDTO dto) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrada não encontrada"));

        if (dto.getFornecedorId() != null) {
            Fornecedor f = fornecedorRepository.findById(dto.getFornecedorId())
                    .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
            entrada.setFornecedor(f);
        } else {
            entrada.setFornecedor(null);
        }

        if (dto.getUsuarioId() != null) {
            Usuario u = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            entrada.setUsuario(u);
        } else {
            entrada.setUsuario(null);
        }

        entrada.setDataPedido(dto.getDataPedido());

        entrada = entradaRepository.save(entrada);
        return EntradaMapper.toResponseDTO(entrada);
    }

    public void delete(Long id) {
        if (!entradaRepository.existsById(id)) {
            throw new RuntimeException("Entrada não encontrada");
        }
        entradaRepository.deleteById(id);
    }
}
