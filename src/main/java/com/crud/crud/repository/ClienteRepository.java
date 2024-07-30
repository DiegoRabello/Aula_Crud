package com.crud.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.crud.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
