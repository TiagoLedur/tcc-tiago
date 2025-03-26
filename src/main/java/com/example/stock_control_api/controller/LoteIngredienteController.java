package com.example.stock_control_api.controller;

import com.example.stock_control_api.model.LoteIngrediente;
import com.example.stock_control_api.service.LoteIngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lotes")
@RequiredArgsConstructor
public class LoteIngredienteController {

    private final LoteIngredienteService loteIngredienteService;

    @GetMapping
    public List<LoteIngrediente> listAll() {
        return loteIngredienteService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LoteIngrediente>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(loteIngredienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LoteIngrediente> create(@RequestBody LoteIngrediente loteIngrediente) {
        LoteIngrediente novoLote = loteIngredienteService.save(loteIngrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteIngrediente> update(@PathVariable Long id, @RequestBody LoteIngrediente loteIngrediente) {
        loteIngrediente.setId(id);
        return ResponseEntity.ok(loteIngredienteService.save(loteIngrediente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loteIngredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
