package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.relatorios.MovimentacaoRelatorioDTO;
import com.example.stock_control_api.model.ItensEntrada;
import com.example.stock_control_api.model.ItensSaida;
import com.example.stock_control_api.repository.ItensEntradaRepository;
import com.example.stock_control_api.repository.ItensSaidaRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioMovimentacaoService {

    private final ItensEntradaRepository itensEntradaRepository;
    private final ItensSaidaRepository itensSaidaRepository;

    public byte[] gerarRelatorioMovimentacoes() {
        try {
            List<MovimentacaoRelatorioDTO> dtoList = new ArrayList<>();

            List<ItensEntrada> itensEntrada = itensEntradaRepository.findAll();
            for (ItensEntrada item : itensEntrada) {
                var entrada = item.getEntrada();
                if (entrada == null) continue;

                Long id = entrada.getId();
                String tipo = "E";
                String ingrediente = item.getIngrediente() != null ? item.getIngrediente().getNome() : "";
                Double quantidade = item.getQuantidade() != null ? item.getQuantidade().doubleValue() : 0.0;
                String fornecedor = entrada.getFornecedor() != null ? entrada.getFornecedor().getNome() : "N/C";
                String usuario = entrada.getUsuario() != null ? entrada.getUsuario().getNome() : "";
                Date data = entrada.getDataEntrada() != null
                        ? Date.from(entrada.getDataEntrada().atZone(ZoneId.systemDefault()).toInstant())
                        : null;

                dtoList.add(new MovimentacaoRelatorioDTO(id, tipo, ingrediente, quantidade, fornecedor, usuario, data));
            }

            List<ItensSaida> itensSaida = itensSaidaRepository.findAll();
            for (ItensSaida item : itensSaida) {
                var saida = item.getSaida();
                if (saida == null) continue;

                Long id = saida.getId();
                String tipo = "S";
                String ingrediente = item.getIngrediente() != null ? item.getIngrediente().getNome() : "";
                Double quantidade = item.getQuantidade() != null ? item.getQuantidade().doubleValue() : 0.0;
                String fornecedor = "N/C";
                String usuario = saida.getUsuario() != null ? saida.getUsuario().getNome() : "";
                Date data = saida.getDataSaida() != null
                        ? Date.from(saida.getDataSaida().atZone(ZoneId.systemDefault()).toInstant())
                        : null;

                dtoList.add(new MovimentacaoRelatorioDTO(id, tipo, ingrediente, quantidade, fornecedor, usuario, data));
            }

            dtoList.sort((a, b) -> {
                if (a.getData() == null && b.getData() == null) return 0;
                if (a.getData() == null) return -1;
                if (b.getData() == null) return 1;
                return a.getData().compareTo(b.getData());
            });

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dtoList);

            InputStream reportStream = getClass().getResourceAsStream("/reports/Movimentacoes.jrxml");
            if (reportStream == null) {
                throw new RuntimeException("Arquivo de relatório '/reports/Movimentacoes.jrxml' não encontrado no classpath.");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            HashMap<String, Object> params = new HashMap<>();
            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, dataSource);

            return JasperExportManager.exportReportToPdf(print);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório de movimentações: " + e.getMessage(), e);
        }
    }
}
