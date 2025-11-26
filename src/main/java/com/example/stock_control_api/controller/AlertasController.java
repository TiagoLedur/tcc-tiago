package com.example.stock_control_api.controller;

import com.example.stock_control_api.dto.alertas.AlertaDTO;
import com.example.stock_control_api.service.AlertasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertasController {

    private final AlertasService alertasService;

    @GetMapping("/estoque")
    public ResponseEntity<List<AlertaDTO>> estoqueBaixo() {
        return ResponseEntity.ok(alertasService.buscarAlertasEstoqueBaixo());
    }

    @GetMapping("/validade")
    public ResponseEntity<List<AlertaDTO>> validadeProxima(@RequestParam(name = "dias", defaultValue = "7") int dias) {
        return ResponseEntity.ok(alertasService.buscarAlertasValidadeProxima(dias));
    }

    @GetMapping
    public ResponseEntity<List<AlertaDTO>> todos(@RequestParam(name = "diasValidade", defaultValue = "7") int diasValidade) {
        return ResponseEntity.ok(alertasService.buscarTodosAlertas(diasValidade));
    }
}
