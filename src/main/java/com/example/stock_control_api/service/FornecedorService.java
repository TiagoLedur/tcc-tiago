package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.fornecedor.FornecedorRequestDTO;
import com.example.stock_control_api.dto.fornecedor.FornecedorResponseDTO;
import com.example.stock_control_api.mapper.FornecedorMapper;
import com.example.stock_control_api.model.Fornecedor;
import com.example.stock_control_api.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    private static final String ERROR = "Fornecedor não encontrado";

    public List<FornecedorResponseDTO> listarTodos() {
        return fornecedorRepository.findAll().stream()
                .map(FornecedorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FornecedorResponseDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERROR));
        return FornecedorMapper.toDTO(fornecedor);
    }

    public FornecedorResponseDTO salvar(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = FornecedorMapper.toEntity(dto);
        fornecedor = fornecedorRepository.save(fornecedor);
        return FornecedorMapper.toDTO(fornecedor);
    }

    public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor existente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ERROR));

        existente.setNome(dto.getNome());
        existente.setContato(dto.getContato());
        existente.setCnpj(dto.getCnpj());

        return FornecedorMapper.toDTO(fornecedorRepository.save(existente));
    }

    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new EntityNotFoundException(ERROR);
        }
        fornecedorRepository.deleteById(id);
    }
}
