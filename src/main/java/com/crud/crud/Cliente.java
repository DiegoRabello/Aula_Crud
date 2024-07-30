package com.crud.crud;

import java.util.ArrayList;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "cliente")
public class Cliente {

    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Nome",nullable = false,length = 255)
    private String nome;
    
    @Column(name = "Cpf",nullable = false,unique = true,length = 11)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    
    @Column(name = "telefon")
    private String telefone;
    @Column(name = "Email",unique = true)
    private String email;
    
    @Column(name = "Data Nascimento")
    private String dataNascimento;
  
}