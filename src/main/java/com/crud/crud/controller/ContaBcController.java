package com.crud.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.crud.Cliente;
import com.crud.crud.ContaBancaria;
import com.crud.crud.service.ClienteService;
import com.crud.crud.service.ContaBcService;

@RestController
@RequestMapping("contaBc")
public class ContaBcController {

    @Autowired
    private ContaBcService contaBcService;
    @Autowired
    private ClienteService clienteService;

    // Coletar Todas as Contas
    @GetMapping
    public ResponseEntity<List<ContaBancaria>> getAllContaBc() {
        List<ContaBancaria> contaBc = contaBcService.getAllContasBc();
        return ResponseEntity.ok(contaBc);
    }

    // Criar Conta Para um cliente
    @PostMapping
    public ResponseEntity<ContaBancaria> Create(@RequestBody ContaBancaria contaBancaria) {
        if (contaBancaria.getCliente() == null || contaBancaria.getCliente().getId() == null) {
            return ResponseEntity.badRequest().body(null); // ou alguma outra lógica de erro
        }

        Cliente cliente = clienteService.getById(contaBancaria.getCliente().getId());

        if (cliente == null) {
            return ResponseEntity.badRequest().body(null); // ou alguma outra lógica de erro
        }
        contaBancaria.setCliente(cliente);

        ContaBancaria contaCriada = contaBcService.createContaBc(contaBancaria);
        return ResponseEntity.ok(contaCriada);
    }

    // Get ContaBancaria ByNúmero
    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> getContaByNumber(@PathVariable Long numeroConta) {
        ContaBancaria conta = contaBcService.getContaByNumber(numeroConta);
        return ResponseEntity.ok(conta);
    }

     @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> update(@PathVariable Long numeroConta, @RequestBody Cliente cliente ,@RequestBody ContaBancaria contaBancaria) {
        ContaBancaria contaBancariaexistente = contaBcService.getContaByNumber(numeroConta);

        if (contaBancariaexistente == null) {
            return ResponseEntity.notFound().build();
        }

        contaBancariaexistente.setTipoConta(contaBancaria.getTipoConta());
        
        ContaBancaria contaBancariasalva = contaBcService.createContaBc(contaBancariaexistente);
        return ResponseEntity.ok(contaBancariasalva);
    }
}
