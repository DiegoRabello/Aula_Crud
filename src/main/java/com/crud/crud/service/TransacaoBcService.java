package com.crud.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.crud.ContaBancaria;
import com.crud.crud.TransacaoBc;
import com.crud.crud.repository.TransacaoBcRepository;

public class TransacaoBcService {
     @Autowired
    TransacaoBcRepository transacaoBcRepository;

    @Autowired
    ContaBcService contaBancariaService;

    public List<TransacaoBc> getAll() {
        return transacaoBcRepository.findAll();
    }

    public TransacaoBc getById(Long id) {
        return transacaoBcRepository.findById(id).orElse(null);
    }

    public List<TransacaoBc> getExtrato(Long id) {
        // Tratar possível erro de retorno null
        ContaBancaria conta = contaBancariaService.getById(id);

        return transacaoBcRepository.findByContaOrigemOrContaDestinoOrderByDataDesc(conta, conta);
    }

    public TransacaoBc create(TransacaoBc novaTransacao) {
        // Tratar a possibilidade de valores nulos nos atributos da transacao
        TransacaoBc transacao = new TransacaoBc();

        ContaBancaria contaOrigem = contaBancariaService.getById(novaTransacao.getContaOrigem().getNumeroConta());
        ContaBancaria contaDestino = contaBancariaService.getById(novaTransacao.getContaDestino().getNumeroConta());

        contaOrigem.sacar(novaTransacao.getValor());
        contaDestino.depositar(novaTransacao.getValor());

        contaBancariaService.create(contaDestino);
        contaBancariaService.create(contaOrigem);
        
        transacao.setValor(novaTransacao.getValor());
        transacao.setContaDestino(contaBancariaService.getById(novaTransacao.getContaDestino().getNumeroConta()));
        transacao.setContaOrigem(contaBancariaService.getById(novaTransacao.getContaOrigem().getNumeroConta()));
        transacao.setTipoTransacao(novaTransacao.getTipoTransacao());

        return transacaoBcRepository.save(transacao);
    }

    public TransacaoBc update(Long id, TransacaoBc novaTransacao) {
        TransacaoBc transacao = transacaoBcRepository.findById(id).orElse(null);
        if (novaTransacao == null) {
            return null;
        }
        // Incluir os atributos que deseja atualizar
        return transacaoBcRepository.save(transacao);
    }

    public void delete(Long id) {
        transacaoBcRepository.deleteById(id);
    }

}
