package com.example.stock_control_api.controller;

import com.example.stock_control_api.service.RelatorioIngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios/ingredientes")
@RequiredArgsConstructor
public class RelatorioIngredienteController {

    private final RelatorioIngredienteService service;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> gerar() {
        byte[] pdf = service.gerarRelatorio();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ingredientes.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
