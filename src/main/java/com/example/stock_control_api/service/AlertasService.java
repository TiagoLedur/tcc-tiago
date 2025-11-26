package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.alertas.AlertaDTO;
import com.example.stock_control_api.enums.AlertaTipo;
import com.example.stock_control_api.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertasService {

    private final IngredienteRepository ingredienteRepository;

    public List<AlertaDTO> buscarAlertasEstoqueBaixo() {
        return ingredienteRepository.findAll().stream()
                .filter(i -> i.getQuantidadeTotal().compareTo(i.getQuantidadeMinima()) < 0)
                .map(i -> new AlertaDTO(
                        i.getId(),
                        i.getNome(),
                        AlertaTipo.ESTOQUE_BAIXO,
                        "Quantidade atual: " + i.getQuantidadeTotal() + " | Quantidade mínima: " + i.getQuantidadeMinima()
                ))
                .collect(Collectors.toList());
    }

    public List<AlertaDTO> buscarAlertasValidadeProxima(int dias) {
        LocalDate limite = LocalDate.now().plusDays(dias);
        return ingredienteRepository.findAll().stream()
                .filter(i -> !i.getValidade().isAfter(limite))
                .map(i -> new AlertaDTO(
                        i.getId(),
                        i.getNome(),
                        AlertaTipo.VALIDADE_PROXIMA,
                        "Validade: " + i.getValidade().toString()
                ))
                .collect(Collectors.toList());
    }

    public List<AlertaDTO> buscarTodosAlertas(int diasValidade) {
        List<AlertaDTO> estoque = buscarAlertasEstoqueBaixo();
        List<AlertaDTO> validade = buscarAlertasValidadeProxima(diasValidade);
        estoque.addAll(validade);
        return estoque;
    }
}
