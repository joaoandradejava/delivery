package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private CrudProdutoService crudProdutoService;

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
}
