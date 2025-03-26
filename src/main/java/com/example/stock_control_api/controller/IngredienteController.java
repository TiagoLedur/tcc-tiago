package com.example.stock_control_api.controller;

import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @GetMapping
    public List<Ingrediente> listAll() {
        return ingredienteService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ingrediente>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Ingrediente> create(@RequestBody Ingrediente ingrediente) {
        Ingrediente novoIngrediente = ingredienteService.save(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoIngrediente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> update(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        ingrediente.setId(id);
        return ResponseEntity.ok(ingredienteService.save(ingrediente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
