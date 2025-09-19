package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.itensentrada.ItensEntradaRequestDTO;
import com.example.stock_control_api.dto.itensentrada.ItensEntradaResponseDTO;
import com.example.stock_control_api.service.ItensEntradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-entrada")
@RequiredArgsConstructor
public class ItensEntradaController {

    private final ItensEntradaService itensEntradaService;

    @GetMapping
    public ResponseEntity<List<ItensEntradaResponseDTO>> listar() {
        return ResponseEntity.ok(itensEntradaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensEntradaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itensEntradaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ItensEntradaResponseDTO> criar(@Valid @RequestBody ItensEntradaRequestDTO dto) {
        return ResponseEntity.ok(itensEntradaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensEntradaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ItensEntradaRequestDTO dto) {
        return ResponseEntity.ok(itensEntradaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itensEntradaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
