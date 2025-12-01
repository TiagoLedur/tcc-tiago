package com.example.stock_control_api.service;

import com.example.stock_control_api.dto.itenssaida.ItensSaidaRequestDTO;
import com.example.stock_control_api.dto.itenssaida.ItensSaidaResponseDTO;
import com.example.stock_control_api.mapper.ItensSaidaMapper;
import com.example.stock_control_api.model.Ingrediente;
import com.example.stock_control_api.model.ItensSaida;
import com.example.stock_control_api.model.Saida;
import com.example.stock_control_api.repository.IngredienteRepository;
import com.example.stock_control_api.repository.ItensSaidaRepository;
import com.example.stock_control_api.repository.SaidaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItensSaidaService {

    private final ItensSaidaRepository itensSaidaRepository;
    private final SaidaRepository saidaRepository;
    private final IngredienteRepository ingredienteRepository;

    public ItensSaidaService(ItensSaidaRepository itensSaidaRepository,
                             SaidaRepository saidaRepository,
                             IngredienteRepository ingredienteRepository) {
        this.itensSaidaRepository = itensSaidaRepository;
        this.saidaRepository = saidaRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    public List<ItensSaidaResponseDTO> findAll() {
        return itensSaidaRepository.findAll().stream()
                .map(ItensSaidaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ItensSaidaResponseDTO findById(Long id) {
        ItensSaida item = itensSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de saída não encontrado"));
        return ItensSaidaMapper.toResponseDTO(item);
    }

    /**
     * NOVO MÉTODO: Valida múltiplos itens ANTES de salvar qualquer um
     * Lança exceção com detalhes dos ingredientes com estoque insuficiente
     */
    public void validarEstoqueParaMultiplosItens(List<ItensSaidaRequestDTO> itens) {
        List<String> erros = new ArrayList<>();

        for (ItensSaidaRequestDTO dto : itens) {
            Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                    .orElseThrow(() -> new RuntimeException("Ingrediente ID " + dto.getIngredienteId() + " não encontrado"));

            BigDecimal estoqueAtual = ingrediente.getQuantidadeTotal();
            BigDecimal quantidadeNecessaria = dto.getQuantidade();

            if (estoqueAtual.compareTo(quantidadeNecessaria) < 0) {
                erros.add(ingrediente.getNome());
            }
        }

        if (!erros.isEmpty()) {
            String mensagem = "Estoque insuficiente para os seguintes ingredientes:\n" + String.join("\n", erros);
            throw new RuntimeException(mensagem);
        }
    }

    public ItensSaidaResponseDTO save(ItensSaidaRequestDTO dto) {
        ItensSaida item = ItensSaidaMapper.toEntity(dto);

        Saida saida = saidaRepository.findById(dto.getSaidaId())
                .orElseThrow(() -> new RuntimeException("Saída não encontrada"));
        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        BigDecimal estoqueAtual = ingrediente.getQuantidadeTotal();
        BigDecimal novaQuantidade = estoqueAtual.subtract(dto.getQuantidade());

        if (novaQuantidade.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Estoque insuficiente para: " + ingrediente.getNome());
        }

        ingrediente.setQuantidadeTotal(novaQuantidade);
        ingredienteRepository.save(ingrediente);

        item.setSaida(saida);
        item.setIngrediente(ingrediente);

        item = itensSaidaRepository.save(item);
        return ItensSaidaMapper.toResponseDTO(item);
    }

    public ItensSaidaResponseDTO update(Long id, ItensSaidaRequestDTO dto) {
        ItensSaida item = itensSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de saída não encontrado"));

        Ingrediente ingrediente = ingredienteRepository.findById(dto.getIngredienteId())
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        // Devolve a quantidade antiga ao estoque
        BigDecimal estoqueAtual = ingrediente.getQuantidadeTotal().add(item.getQuantidade());
        // Subtrai a nova quantidade
        BigDecimal novoEstoque = estoqueAtual.subtract(dto.getQuantidade());

        if (novoEstoque.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Estoque insuficiente para: " + ingrediente.getNome());
        }

        ingrediente.setQuantidadeTotal(novoEstoque);
        ingredienteRepository.save(ingrediente);

        Saida saida = saidaRepository.findById(dto.getSaidaId())
                .orElseThrow(() -> new RuntimeException("Saída não encontrada"));

        item.setSaida(saida);
        item.setIngrediente(ingrediente);
        item.setQuantidade(dto.getQuantidade());

        item = itensSaidaRepository.save(item);
        return ItensSaidaMapper.toResponseDTO(item);
    }

    public void delete(Long id) {
        ItensSaida item = itensSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de saída não encontrado"));

        Long saidaId = item.getSaida().getId();

        Ingrediente ingrediente = item.getIngrediente();
        ingrediente.setQuantidadeTotal(
                ingrediente.getQuantidadeTotal().add(item.getQuantidade())
        );
        ingredienteRepository.save(ingrediente);

        itensSaidaRepository.delete(item);

        if (!itensSaidaRepository.existsBySaidaId(saidaId)) {
            saidaRepository.deleteById(saidaId);
        }
    }
}