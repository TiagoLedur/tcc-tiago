package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.lote.*;
import com.example.stock_control_api.service.LoteIngredienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lote-ingredientes")
@RequiredArgsConstructor
public class LoteIngredienteController {

    private final LoteIngredienteService loteIngredienteService;

    @GetMapping
    public ResponseEntity<List<LoteIngredienteResponseDTO>> list() {
        return ResponseEntity.ok(loteIngredienteService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteIngredienteResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(loteIngredienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LoteIngredienteResponseDTO> save(@Valid @RequestBody LoteIngredienteRequestDTO dto) {
        return ResponseEntity.ok(loteIngredienteService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loteIngredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
