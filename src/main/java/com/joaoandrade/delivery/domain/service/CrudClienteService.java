package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.filter.ClienteFilter;
import com.joaoandrade.delivery.domain.model.Cliente;
import com.joaoandrade.delivery.domain.repository.ClienteRepository;
import com.joaoandrade.delivery.infrastructure.specification.ClienteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudClienteService {
    @Autowired
    private ClienteRepository repository;


    public Page<Cliente> buscarTodos(ClienteFilter clienteFilter, Pageable pageable) {
        return repository.findAll(ClienteSpecification.buscarTodos(clienteFilter), pageable);
    }


    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("O Cliente de id %d não foi encontrado no sistema!", id)));
    }

    @Transactional
    public Cliente inserir(Cliente cliente) {
        return repository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Transactional
    public void deletarPorId(Long id) {
        buscarPorId(id);

        repository.deleteById(id);
    }


}
