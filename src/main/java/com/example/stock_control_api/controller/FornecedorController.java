package com.example.stock_control_api.controller;

import com.example.stock_control_api.model.Fornecedor;
import com.example.stock_control_api.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> listAll() {
        return fornecedorService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Fornecedor>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(fornecedorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Fornecedor> create(@RequestBody Fornecedor fornecedor) {
        Fornecedor novoFornecedor = fornecedorService.save(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        fornecedor.setId(id);
        return ResponseEntity.ok(fornecedorService.save(fornecedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fornecedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
