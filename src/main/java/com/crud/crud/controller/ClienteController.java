package com.crud.crud.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.crud.Cliente;
import com.crud.crud.repository.ClienteRepository;
import com.crud.crud.service.ClienteService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Buscar todos os clientes - getAll
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = clienteService.getAll();
        return ResponseEntity.ok(clientes);
    }

    // Buscar todos os clientes ativos
    @GetMapping("/ativos")
    public ResponseEntity<List<Cliente>> getAllAtivos() {
        List<Cliente> clientes = clienteService.getAllAtivos();
        return ResponseEntity.ok(clientes);
    }
    
    // Buscar um cliente por id - getById
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    // Criar um cliente - create
    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.create(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    // Atualizar um cliente - update
    // Combinação do getById e create
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente clienteNovo) {
        Cliente clienteExistente = clienteService.getById(id);

        if (clienteExistente == null) {
            return ResponseEntity.notFound().build();
        }
        Cliente clienteSalvo = clienteService.update(id, clienteExistente, clienteNovo);

        return ResponseEntity.ok(clienteSalvo);
    }

    // Deletar um cliente - delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Cliente cliente = clienteService.getById(id);

        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteService.delete(id));
    }
}
