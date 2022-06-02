package com.joaoandrade.delivery.domain.service.crud;

import com.joaoandrade.delivery.domain.exception.EnderecoNaoEncontradoException;
import com.joaoandrade.delivery.domain.exception.EntidadeEmUsoException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.Cliente;
import com.joaoandrade.delivery.domain.model.Endereco;
import com.joaoandrade.delivery.domain.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudClienteEnderecoService {

    @Autowired
    private CrudClienteService crudClienteService;

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private MessageSource messageSource;

    private Endereco buscarPorId(Long enderecoId) {
        String[] args = {"endereço", enderecoId.toString()};
        return repository.findById(enderecoId).orElseThrow(() -> new EnderecoNaoEncontradoException(messageSource.getMessage("entidade.nao.encontrada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
    }

    public Endereco buscarEnderecoDoCliente(Long clienteId, Long enderecoId) {
        crudClienteService.buscarPorId(clienteId);
        buscarPorId(enderecoId);

        String[] args = {"endereço", enderecoId.toString(), "cliente", clienteId.toString()};
        return repository.buscarEnderecoDoCliente(clienteId, enderecoId).orElseThrow(() -> new SistemaException(messageSource.getMessage("entidade.nao.esta.associada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
    }


    @Transactional
    public Endereco inserir(Endereco endereco, Long clienteId) {
        Cliente cliente = crudClienteService.buscarPorId(clienteId);
        endereco.setCliente(cliente);

        return repository.save(endereco);
    }

    @Transactional
    public Endereco atualizar(Endereco endereco) {
        return repository.save(endereco);
    }


    @Transactional
    public void deletarEnderecoDoCliente(Long clienteId, Long enderecoId) {
        buscarEnderecoDoCliente(clienteId, enderecoId);

        try {
            repository.deleteById(enderecoId);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            String[] args = {"endereço", enderecoId.toString()};
            throw new EntidadeEmUsoException(messageSource.getMessage("nao.possivel.excluir.entidade.pois.esta.em.uso.id.sistema.substantivo.masculinov", args, LocaleContextHolder.getLocale()));
        }
    }
}
