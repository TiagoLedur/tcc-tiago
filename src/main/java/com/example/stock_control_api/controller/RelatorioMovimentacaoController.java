package com.example.stock_control_api.controller;

import com.example.stock_control_api.service.RelatorioMovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios/movimentacoes")
@RequiredArgsConstructor
public class RelatorioMovimentacaoController {

    private final RelatorioMovimentacaoService service;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> gerar() {
        byte[] pdf = service.gerarRelatorioMovimentacoes();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=movimentacoes.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
