package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.itenssaida.ItensSaidaRequestDTO;
import com.example.stock_control_api.dto.itenssaida.ItensSaidaResponseDTO;
import com.example.stock_control_api.service.ItensSaidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-saida")
@RequiredArgsConstructor
public class ItensSaidaController {

    private final ItensSaidaService itensSaidaService;

    @GetMapping
    public ResponseEntity<List<ItensSaidaResponseDTO>> listar() {
        return ResponseEntity.ok(itensSaidaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensSaidaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itensSaidaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ItensSaidaResponseDTO> criar(@Valid @RequestBody ItensSaidaRequestDTO dto) {
        return ResponseEntity.ok(itensSaidaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItensSaidaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ItensSaidaRequestDTO dto) {
        return ResponseEntity.ok(itensSaidaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itensSaidaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
