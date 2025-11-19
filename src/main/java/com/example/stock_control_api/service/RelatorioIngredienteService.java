package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.relatorios.IngredienteRelatorioDTO;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioIngredienteService {

    private final IngredienteRepository ingredienteRepository;

    public byte[] gerarRelatorio() {

        try {
            List<Ingrediente> ingredientes = ingredienteRepository.findAll();

            List<IngredienteRelatorioDTO> dtoList = ingredientes.stream().map(i ->
                    new IngredienteRelatorioDTO(
                            i.getId(),
                            i.getNome(),
                            i.getCategoria().getNome(),
                            i.getUnidadeMedida(),
                            i.getPrecoUnitario() != null ? i.getPrecoUnitario().doubleValue() : 0.0,
                            i.getQuantidadeTotal() != null ? i.getQuantidadeTotal().doubleValue() : 0.0,
                            Date.from(i.getValidade().atStartOfDay(ZoneId.systemDefault()).toInstant())

                    )
            ).toList();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dtoList);

            InputStream reportStream = getClass().getResourceAsStream("/reports/Ingredientes.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            HashMap<String, Object> params = new HashMap<>();

            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, dataSource);

            return JasperExportManager.exportReportToPdf(print);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório de ingredientes.");
        }
    }
}
