package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.relatorio.RelatorioRequestDTO;
import com.example.stock_control_api.dto.relatorio.RelatorioResponseDTO;
import com.example.stock_control_api.service.RelatorioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<RelatorioResponseDTO>> listar() {
        return ResponseEntity.ok(relatorioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(relatorioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RelatorioResponseDTO> criar(@Valid @RequestBody RelatorioRequestDTO dto) {
        return ResponseEntity.ok(relatorioService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
