package com.crud.crud;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Cep",nullable = false,length = 8)
    private String cep;

    @Column(name = "Logradouro")
    private String logradouro;

    @Column(name = "Número")
    private String numero;

    @Column(name = "Complemento")
    private String complemento;
    
    @Column(name = "Bairro")
    private String bairro;

    @Column(name = "Cidade")
    private String localidade;
}
