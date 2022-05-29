package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.model.Categoria;
import com.joaoandrade.delivery.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudCategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Page<Categoria> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("A Categoria de id %d não foi encontrada no sistema!", id)));
    }

    @Transactional
    public Categoria inserir(Categoria categoria) {
        return repository.save(categoria);
    }

    @Transactional

    public Categoria atualizar(Categoria categoria) {
        return repository.save(categoria);
    }

    @Transactional
    public void deletarPorId(Long id) {
        Categoria categoria = buscarPorId(id);

        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeNaoEncontradaException(String.format("Não foi possivel excluir a Categoria '%s' pois ela está em uso no sistema!", categoria.getNome()));
        }
    }
}
