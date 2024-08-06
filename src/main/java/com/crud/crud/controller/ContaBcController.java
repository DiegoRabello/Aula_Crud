package com.crud.crud.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.crud.Cliente;
import com.crud.crud.ContaBancaria;
import com.crud.crud.TransacaoBc;
import com.crud.crud.service.ClienteService;
import com.crud.crud.service.ContaBcService;

@RestController
@RequestMapping("contaBc")
public class ContaBcController {
   
    @Autowired
    private ContaBcService contaBancariaService;

    @GetMapping
    public ResponseEntity<List<ContaBancaria>> getAll() {
        return ResponseEntity.ok(contaBancariaService.getAll());
    }

    

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contaBancariaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ContaBancaria> create(@RequestBody ContaBancaria contaBancaria) {
        return ResponseEntity.ok(contaBancariaService.create(contaBancaria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> update(@PathVariable Long id, @RequestBody ContaBancaria contaNova) {
        ContaBancaria contaExistente = contaBancariaService.getById(id);

        if (contaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(contaBancariaService.update(contaExistente, contaNova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contaBancariaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
