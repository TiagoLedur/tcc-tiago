package com.example.stock_control_api.service;

import com.example.stock_control_api.model.Movimentacao;
import com.example.stock_control_api.repository.MovimentacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    public Movimentacao salvar(Movimentacao movimentacao) {
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarTodos() {
        return movimentacaoRepository.findAll();
    }

    public List<Movimentacao> listarPorIngrediente(Long ingredienteId) {
        return movimentacaoRepository.findByIngredienteId(ingredienteId);
    }
}

