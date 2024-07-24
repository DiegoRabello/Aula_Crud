package com.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud.Cliente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @GetMapping
    public String getAllClientes() {
        if (Cliente.clientes.isEmpty()) {
            return Cliente.clientes.toString();

        } else {
            return "Não ha clientes cadastrados.";
        }
    }

    @GetMapping
    public String getClientesById() {
        for (Cliente clienteds: Cliente.getClientes().size())
        
    }

    @PostMapping
    public String addCliente(@RequestBody Cliente cliente) {
        System.out.println("Adicionando um Cliente...");
        Cliente.clientes.add(cliente);
        return "Cliente cadastrado com Sucesso!";
    }@PutMapping

}
