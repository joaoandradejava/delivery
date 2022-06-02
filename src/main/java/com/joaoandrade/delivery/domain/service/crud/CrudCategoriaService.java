package com.joaoandrade.delivery.domain.service.crud;

import com.joaoandrade.delivery.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.model.Categoria;
import com.joaoandrade.delivery.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudCategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private MessageSource messageSource;

    public Page<Categoria> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Categoria> buscarTodosPorNome(String nome, Pageable pageable) {
        return repository.buscarTodosPorNome(nome, pageable);
    }

    public Categoria buscarPorId(Long id) {
        String[] args = {"categoria", id.toString()};
        return repository.findById(id).orElseThrow(() -> new CategoriaNaoEncontradaException(messageSource.getMessage("entidade.nao.encontrada.substantivo.feminino", args, LocaleContextHolder.getLocale())));
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
            String[] args = {"categoria", categoria.getNome()};
            throw new EntidadeNaoEncontradaException(messageSource.getMessage("nao.possivel.excluir.entidade.pois.esta.em.uso.sistema.substantivo.feminino", args, LocaleContextHolder.getLocale()));
        }
    }
}
