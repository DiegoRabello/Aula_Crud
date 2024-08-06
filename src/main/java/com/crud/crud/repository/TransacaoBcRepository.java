package com.crud.crud.repository;

import java.util.List;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crud.ContaBancaria;
import com.crud.crud.TransacaoBc;

public interface TransacaoBcRepository extends JpaRepository<TransacaoBc,Long>{
    List<TransacaoBc> findByContaOrigemOrContaDestinoOrderByDataDesc(
        ContaBancaria contaOrigem, 
        ContaBancaria contaDestino
    );

}
