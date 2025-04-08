package com.example.stock_control_api.controller;

import com.example.stock_control_api.model.Movimentacao;
import com.example.stock_control_api.service.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<Movimentacao> criarMovimentacao(@RequestBody Movimentacao movimentacao) {
        Movimentacao salva = movimentacaoService.salvar(movimentacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> listarTodas() {
        return ResponseEntity.ok(movimentacaoService.listarTodos());
    }

    @GetMapping("/ingrediente/{id}")
    public ResponseEntity<List<Movimentacao>> listarPorIngrediente(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoService.listarPorIngrediente(id));
    }
}

