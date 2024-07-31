package com.crud.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.crud.Cliente;
import com.crud.crud.ContaBancaria;
import com.crud.crud.repository.ClienteRepository;
import com.crud.crud.repository.ContaBcRepository;

public class ContaBcService {
     @Autowired
    private ContaBcRepository contaBcRepository;

    public List<ContaBancaria> getAllContasBc() {
        return contaBcRepository.findAll();
    }
    public ContaBancaria createContaBc(ContaBancaria contaBancaria) {
        return contaBcRepository.save(contaBancaria);
    }
    public ContaBancaria getContaByNumber(Long numeroConta) {
       return contaBcRepository.findById(numeroConta).orElse(null);
    }
}
