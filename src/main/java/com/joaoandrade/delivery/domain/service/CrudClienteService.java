package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.filter.ClienteFilter;
import com.joaoandrade.delivery.domain.model.Cliente;
import com.joaoandrade.delivery.domain.repository.ClienteRepository;
import com.joaoandrade.delivery.infrastructure.specification.ClienteSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudClienteService {
    @Autowired
    private ClienteRepository repository;


    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Cliente> buscarTodos(ClienteFilter clienteFilter, Pageable pageable) {
        return repository.findAll(ClienteSpecification.buscarTodos(clienteFilter), pageable);
    }


    public Cliente buscarPorId(Long id) {
        String[] args = {"cliente", id.toString()};
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(messageSource.getMessage("entidade.nao.encontrada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
    }

    @Transactional
    public Cliente inserir(Cliente cliente) {
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));

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
