package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.ingrediente.IngredienteRequestDTO;
import com.example.stock_control_api.dto.ingrediente.IngredienteResponseDTO;

import com.example.stock_control_api.service.IngredienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<IngredienteResponseDTO> criar(@RequestBody @Valid IngredienteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.criarIngrediente(dto));
    }

    @GetMapping
    public List<IngredienteResponseDTO> listarTodos() {
        return ingredienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteResponseDTO> atualizar(@PathVariable Long id,
                                                            @RequestBody @Valid IngredienteRequestDTO dto) {
        return ResponseEntity.ok(ingredienteService.atualizarIngrediente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ingredienteService.deletarIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}

