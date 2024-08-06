package com.crud.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.crud.ContaBancaria;
import com.crud.crud.TransacaoBc;
import com.crud.crud.service.ContaBcService;
import com.crud.crud.service.TransacaoBcService;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("transferencia")
public class TransacaoBcController {
    @Autowired
    private TransacaoBcService transacaoService;

    @Autowired
    private ContaBcService contaBancariaService;

    @GetMapping
    public ResponseEntity<List<TransacaoBc>> getAll() {
        return ResponseEntity.ok(transacaoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoBc> getById(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.getById(id));
    }

    @GetMapping("/extrato/{id}")
    public ResponseEntity<List<TransacaoBc>> getExtrato (@PathVariable Long idConta) {
        List<TransacaoBc> extrato = transacaoService.getExtrato(idConta);
        
        if (extrato.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(extrato);
    }

    @PostMapping
    public ResponseEntity<TransacaoBc> create(@RequestBody TransacaoBc transacao) {
        // Verificar se alguma das contas da transação são nulas
        if(contaBancariaService.temSaldo(transacao)){
            return ResponseEntity.ok(transacaoService.create(transacao));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoBc> update(@PathVariable Long id, @RequestBody TransacaoBc transacao) {
        return ResponseEntity.ok(transacaoService.update(id, transacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
