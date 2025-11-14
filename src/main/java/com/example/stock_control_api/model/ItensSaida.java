package com.example.stock_control_api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_saida")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItensSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saida_id", nullable = false)
    private Saida saida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id", nullable = false)
    private Ingrediente ingrediente;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidade;
}
