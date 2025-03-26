package com.example.stock_control_api.service;

import com.example.stock_control_api.model.Fornecedor;
import com.example.stock_control_api.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public List<Fornecedor> list() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> findById(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor save(Fornecedor categoria) {
        return fornecedorRepository.save(categoria);
    }

    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

}
