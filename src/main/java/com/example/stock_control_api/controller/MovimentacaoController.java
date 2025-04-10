package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.MovimentacaoRequestDTO;
import com.example.stock_control_api.dto.MovimentacaoResponseDTO;
import com.example.stock_control_api.service.MovimentacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @PostMapping
    public MovimentacaoResponseDTO criar(@Valid @RequestBody MovimentacaoRequestDTO dto) {
        return movimentacaoService.create(dto);
    }

    @GetMapping
    public List<MovimentacaoResponseDTO> listar() {
        return movimentacaoService.list();
    }
}
