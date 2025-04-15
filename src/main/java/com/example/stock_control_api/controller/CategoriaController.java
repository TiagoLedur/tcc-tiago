package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.categoria.CategoriaRequestDTO;
import com.example.stock_control_api.dto.categoria.CategoriaResponseDTO;
import com.example.stock_control_api.mapper.CategoriaMapper;
import com.example.stock_control_api.model.Categoria;
import com.example.stock_control_api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@Valid @RequestBody CategoriaRequestDTO dto) {
        Categoria categoria = categoriaService.save(CategoriaMapper.toEntity(dto));
        return ResponseEntity.ok(CategoriaMapper.toDTO(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        List<CategoriaResponseDTO> categorias = categoriaService.list()
                .stream()
                .map(CategoriaMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(CategoriaMapper.toDTO(categoria));
    }
}
