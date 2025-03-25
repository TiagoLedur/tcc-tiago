package com.example.stock_control_api.service;

import com.example.stock_control_api.model.Fornecedor;
import com.example.stock_control_api.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Fornecedor salvar(Fornecedor categoria) {
        return fornecedorRepository.save(categoria);
    }

    public void deletar(Long id) {
        fornecedorRepository.deleteById(id);
    }

}
