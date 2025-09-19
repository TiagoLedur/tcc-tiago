package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.entrada.EntradaRequestDTO;
import com.example.stock_control_api.dto.entrada.EntradaResponseDTO;
import com.example.stock_control_api.service.EntradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradas")
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;

    @GetMapping
    public ResponseEntity<List<EntradaResponseDTO>> listar() {
        return ResponseEntity.ok(entradaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(entradaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EntradaResponseDTO> criar(@Valid @RequestBody EntradaRequestDTO dto) {
        return ResponseEntity.ok(entradaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntradaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EntradaRequestDTO dto) {
        return ResponseEntity.ok(entradaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entradaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
