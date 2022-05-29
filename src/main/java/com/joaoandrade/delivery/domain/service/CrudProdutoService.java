package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.EntidadeEmUsoException;
import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.Categoria;
import com.joaoandrade.delivery.domain.model.Produto;
import com.joaoandrade.delivery.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CrudCategoriaService crudCategoriaService;

    public Produto buscarPorId(String id) {
        String[] args = {"produto", id};
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(messageSource.getMessage("entidade.nao.encontrada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
    }

    @Transactional
    public Produto inserir(Produto produto) {
        Categoria categoria = crudCategoriaService.buscarPorId(produto.getCategoria().getId());
        produto.setCategoria(categoria);

        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(Produto produto) {
        Categoria categoria = crudCategoriaService.buscarPorId(produto.getCategoria().getId());
        produto.setCategoria(categoria);

        return repository.save(produto);
    }

    @Transactional
    public void deletarPorId(String id) {
        Produto produto = buscarPorId(id);

        try {
            repository.deleteById(id);
            repository.flush();
        } catch (DataIntegrityViolationException e) {
            String[] args = {"produto", produto.getNome()};
            throw new EntidadeEmUsoException(messageSource.getMessage("nao.possivel.excluir.entidade.pois.esta.em.uso.sistema.substantivo.masculino", args, LocaleContextHolder.getLocale()));
        }
    }
}
