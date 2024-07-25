package com.crud.crud.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.*;
import com.crud.crud.Cliente;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/get_cliente_by_id/{id}")
    public String getClientesById(@PathVariable UUID id) {
        for (Cliente cliente : Cliente.clientes) {
            if (cliente.getId().equals(id)) {
                return cliente.toString();
            }
        }
        return "Cliente não Encontrado!";

    }

    @PostMapping("/add_cliente")
    public String addCliente(@RequestBody Cliente cliente) {
        System.out.println("Adicionando um Cliente...");
        Cliente.clientes.add(cliente);
        return "Cliente cadastrado com Sucesso!";
    }

    @DeleteMapping("/delete_cliente/{id}")
    public String deleteCliente(@PathVariable UUID id) {
        for (Cliente cliente : Cliente.clientes) {
            if (cliente.getId().equals(id)) {
                Cliente.clientes.remove(cliente);
            }
        }
        return "Cliente Deletado Com Sucesso";
    }

    @PutMapping("/atualiza_cliente/{id}")
    public String atualizaClienteById(@PathVariable UUID id, @RequestBody Cliente cliente) {
        for (Cliente cliente1 : Cliente.clientes) {
            if (cliente1.getId().equals(id)) {
                cliente1.setNome(cliente.getNome());
                cliente1.setCpf(cliente.getCpf());
                cliente1.setEndereco(cliente.getEndereco());

            }
        }
        return "Cadastro Atualizado com Sucesso!";

    }

}
