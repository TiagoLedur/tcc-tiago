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
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário autenticado não encontrado"));

        entrada.setUsuario(usuario);

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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário autenticado não encontrado"));

        entrada.setUsuario(usuario);

        entrada = entradaRepository.save(entrada);
        return EntradaMapper.toResponseDTO(entrada);
    }

    public void delete(Long id) {
        if (!entradaRepository.existsById(id)) {
            throw new RuntimeException("Entrada não encontrada");
        }
        entradaRepository.deleteById(id);
    }

    public List<EntradaResponseDTO> filtrarEntradas(Long id,
                                                    Long fornecedorId,
                                                    Long ingredienteId,
                                                    LocalDateTime dataInicio,
                                                    LocalDateTime dataFim,
                                                    String sortBy,
                                                    Sort.Direction direction) {

        List<Entrada> entradas = entradaRepository.filtrarEntradas(
                id, fornecedorId, ingredienteId, dataInicio, dataFim
        );

        if (sortBy != null && direction != null) {

            if ("dataEntrada".equals(sortBy)) {
                entradas.sort((e1, e2) ->
                        direction.isAscending()
                                ? e1.getDataEntrada().compareTo(e2.getDataEntrada())
                                : e2.getDataEntrada().compareTo(e1.getDataEntrada())
                );
            }
        }

        return entradas.stream()
                .map(EntradaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


}