package com.example.stock_control_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "entradas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data_entrada", updatable = false)
    private LocalDateTime dataEntrada = LocalDateTime.now();

    @OneToMany(mappedBy = "entrada", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensEntrada> itensEntrada;
}
