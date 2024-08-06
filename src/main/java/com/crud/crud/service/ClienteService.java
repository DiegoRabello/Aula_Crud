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

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id)
                                .orElse(null);
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteExistente, Cliente clienteNovo) {

        if (clienteNovo.getNome() != null) {
            clienteExistente.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getCpf() != null) {
            clienteExistente.setCpf(clienteNovo.getCpf());
        }
        if (clienteNovo.getEndereco() != null) {
            clienteExistente.setEndereco(clienteNovo.getEndereco());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteExistente.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getEmail() != null) {
            clienteExistente.setEmail(clienteNovo.getEmail());
        }
        if (clienteNovo.getDataNascimento() != null) {
            clienteExistente.setDataNascimento(clienteNovo.getDataNascimento());
        }
        if (clienteNovo.isClienteAtivo() != clienteExistente.isClienteAtivo()) {
            clienteExistente.setClienteAtivo(clienteNovo.isClienteAtivo());
        }

        return clienteRepository.save(clienteExistente);
    }

    public Cliente delete(Long id) {
        // Delete anterior
        clienteRepository.deleteById(id);

        // Delete lógico
        Cliente cliente = getById(id);
        cliente.setClienteAtivo(false);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllAtivos() {
        return clienteRepository.findByClienteAtivoTrue();
    }
}
