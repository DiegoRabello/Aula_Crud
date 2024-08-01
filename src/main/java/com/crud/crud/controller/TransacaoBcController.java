package com.crud.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.crud.crud.ContaBancaria;
import com.crud.crud.TransacaoBc;
import com.crud.crud.service.ContaBcService;

import io.micrometer.core.ipc.http.HttpSender.Response;
@RestController
@RequestMapping("transferencia")
public class TransacaoBcController {

    @Autowired
    private ContaBcService contaBcService; // Serviço para gerenciar clientes
    @Autowired
    private TransacaoBc transacaoBc; // Serviço para gerenciar transações

    @PostMapping
    public ResponseEntity<TransacaoBc> TransferBC(@RequestBody TransacaoBc transacaoBc) {
        ContaBancaria contaOrigem = contaBcService.getContaByNumber(transacaoBc.getContaOrigem().getNumeroConta());
        ContaBancaria contaDestino = contaBcService.getContaByNumber(transacaoBc.getContaDestino().getNumeroConta());

        if (contaOrigem == null || contaDestino == null) {
            return ResponseEntity.notFound().build();

        }

        double verSaldo = contaOrigem.getSaldo();
        double valorTransacao = transacaoBc.getValorTransacao();

        // Verificar se a conta de origem tem saldo suficiente
        if (verSaldo < valorTransacao) {
            return ResponseEntity.badRequest().build();
        }

        // Realizar a transferência
        contaOrigem.setSaldo(contaOrigem.getSaldo() - (valorTransacao));

        contaDestino.setSaldo(contaDestino.getSaldo() + (valorTransacao));

        // Salvar as contas atualizadas
        contaBcService.createContaBc(contaOrigem);
        contaBcService.createContaBc(contaDestino);

        return ResponseEntity.ok().build();

    }
}
