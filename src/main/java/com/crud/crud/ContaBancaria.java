package com.crud.crud;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Conta Bancaria")
public class ContaBancaria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroConta;

    @Column(nullable = false)
    private double saldo = 0.0;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente titular;

    public void depositar(double valor) {
        saldo += valor;
    }
    public void sacar(double valor) {
        saldo -= valor;
    }
   }
