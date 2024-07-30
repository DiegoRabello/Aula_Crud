package com.crud.crud.service;

import java.util.List;

import org.aspectj.apache.bcel.generic.InstructionConstants.Clinit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crud.crud.Cliente;
import com.crud.crud.repository.ClienteRepository;

import io.micrometer.core.ipc.http.HttpSender.Response;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
    public Cliente getById (Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    public void delete (Long id) {
        clienteRepository.deleteById(id);
    }
    public Cliente create (Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente upCliente( Long id) {
        Cliente cliente = getById(id);
        if (cliente != null) {
            return null;
        }

        cliente.setNome(cliente.getNome());
        cliente.setCpf(cliente.getCpf());
        cliente.setEndereco(cliente.getEndereco());
        cliente.setTelefone(cliente.getTelefone());
        cliente.setEmail(cliente.getEmail());
        cliente.setDataNascimento(cliente.getDataNascimento());
        return clienteRepository.save(cliente);
        
    }
}
