package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.ImagemProduto;
import com.joaoandrade.delivery.domain.model.Produto;
import com.joaoandrade.delivery.domain.service.crud.CrudProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private CrudProdutoService crudProdutoService;

    @Autowired
    private MessageSource messageSource;


    @Transactional
    public void adicionarQuantidadeEstoque(Integer quantidade, String id) {
        Produto produto = crudProdutoService.buscarPorId(id);

        produto.adicionarQuantidadeEstoque(quantidade);
    }

    @Transactional
    public void removerQuantidadeEstoque(Integer quantidade, String id) {
        Produto produto = crudProdutoService.buscarPorId(id);

        produto.removerQuantidadeEstoque(quantidade);
    }


    @Transactional
    public void adicionarDesconto(Integer porcentagemDesconto, String id) {
        Produto produto = crudProdutoService.buscarPorId(id);

        if (produto.getTemDesconto()) {
            throw new SistemaException(messageSource.getMessage("produto.ja.tem.desconto", null, LocaleContextHolder.getLocale()));
        }

        produto.adicionarDesconto(porcentagemDesconto);
    }

    @Transactional
    public void removerDesconto(String id) {
        Produto produto = crudProdutoService.buscarPorId(id);

        produto.removerDesconto();
    }

    @Transactional
    public void adicionarImagem(byte[] conteudo, String id) {
        Produto produto = crudProdutoService.buscarPorId(id);
        if (produto.getImagem() != null) {
            throw new SistemaException(messageSource.getMessage("produto.ja.possui.imagem", null, LocaleContextHolder.getLocale()));
        }

        ImagemProduto imagemProduto = new ImagemProduto(null, conteudo, produto);
        produto.setImagem(imagemProduto);
    }

    @Transactional
    public void removerImagem(String id) {
        Produto produto = crudProdutoService.buscarPorId(id);

        if (produto.getImagem() != null) {
            produto.setImagem(null);
        }

    }
}
