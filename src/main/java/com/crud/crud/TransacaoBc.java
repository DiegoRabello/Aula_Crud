package com.crud.crud;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaçãoBc")
public class TransacaoBc {
    @ManyToOne
    @JoinColumn(name = "conta_origem", referencedColumnName = "numeroConta")
    private ContaBancaria contaOrigem;
    @ManyToOne
    @JoinColumn(name = "conta_destino", referencedColumnName = "numeroConta")
    private ContaBancaria contaDestino;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_transação")
    private double valorTransacao;

}
