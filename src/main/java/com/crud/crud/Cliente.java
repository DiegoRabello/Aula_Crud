package com.crud.crud;

import java.util.ArrayList;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Cliente {
    public static ArrayList <Cliente> clientes = new ArrayList<Cliente>();

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }
    public static void setClientes(ArrayList<Cliente> clientes) {
        Cliente.clientes = clientes;
    }
    private String nome;
    private String cpf;
    private Endereco endereco;
    private String email;
    private String dataNascimento;

}
