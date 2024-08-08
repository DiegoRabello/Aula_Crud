package com.crud.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.crud.Cliente;
import com.crud.crud.ContaBancaria;
import com.crud.crud.TransacaoBc;
import com.crud.crud.repository.ClienteRepository;
import com.crud.crud.repository.ContaBcRepository;

public class ContaBcService {
    @Autowired
    ContaBcRepository contaBancariaRepository;

    ContaBcService(ContaBcRepository contaBancariaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
    }

    public List<ContaBancaria> getAll() {
        return contaBancariaRepository.findAll();
    }

    public ContaBancaria getById(Long id) {
        return contaBancariaRepository.findById(id).orElse(null);
    }

    public ContaBancaria create(ContaBancaria contaBancaria) {
        return contaBancariaRepository.save(contaBancaria);
    }

    public ContaBancaria update(ContaBancaria contaExistente, ContaBancaria contaNova) {

        contaExistente.setSaldo(contaNova.getSaldo());

        return contaBancariaRepository.save(contaExistente);
    }

    public ContaBancaria delete(Long id) {
        ContaBancaria contaBancaria = getById(id);
        contaBancariaRepository.delete(contaBancaria);
        return contaBancaria;
    }

    public boolean temSaldo(TransacaoBc transacao) {
        // Comprarar o saldo da conta origem com o valor da transacao
        ContaBancaria conta = getById(transacao.getContaOrigem().getNumeroConta());

        boolean temSaldo = (
            conta.getSaldo() 
            >= 
            transacao.getValor()
        );
        return true;
    }
}
