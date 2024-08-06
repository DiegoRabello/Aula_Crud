package com.crud.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crud.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByClienteAtivoTrue();
}
