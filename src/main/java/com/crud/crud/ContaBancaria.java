package com.crud.crud;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Conta Bancaria")
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroConta;

    @Column(name = "agência", nullable = false)
    private int agencia;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "tipo_conta", nullable = false)
    private String tipoConta;
    @Column(nullable = false)
    
    private double saldo = 0;

    public static void geraNumeroConta() {
        int inicio = 1000;
        int fim = 9999;
        int contador = inicio - 1;
        if (contador < fim) {
           contador++;
        } else {
            throw new RuntimeException("O limite máximo de números foi alcançado.");
        }

    }
}
