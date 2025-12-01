package com.example.stock_control_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "saidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Saida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data_saida", updatable = false)
    private LocalDateTime dataSaida = LocalDateTime.now();

    @OneToMany(mappedBy = "saida", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensSaida> itensSaida;
}
