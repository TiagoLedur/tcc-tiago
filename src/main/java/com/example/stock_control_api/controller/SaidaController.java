package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.saida.SaidaRequestDTO;
import com.example.stock_control_api.dto.saida.SaidaResponseDTO;
import com.example.stock_control_api.service.SaidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saidas")
@RequiredArgsConstructor
public class SaidaController {

    private final SaidaService saidaService;

    @GetMapping
    public ResponseEntity<List<SaidaResponseDTO>> listar() {
        return ResponseEntity.ok(saidaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(saidaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SaidaResponseDTO> criar(@Valid @RequestBody SaidaRequestDTO dto) {
        return ResponseEntity.ok(saidaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaidaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody SaidaRequestDTO dto) {
        return ResponseEntity.ok(saidaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        saidaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
