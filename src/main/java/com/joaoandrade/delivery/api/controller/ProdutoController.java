package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.ProdutoFullModelAssembler;
import com.joaoandrade.delivery.api.disassembler.ProdutoInputDisassembler;
import com.joaoandrade.delivery.api.input.ProdutoEstoqueInput;
import com.joaoandrade.delivery.api.input.ProdutoInput;
import com.joaoandrade.delivery.api.model.ProdutoFullModel;
import com.joaoandrade.delivery.api.model.ProdutoModel;
import com.joaoandrade.delivery.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.Produto;
import com.joaoandrade.delivery.domain.service.CrudProdutoService;
import com.joaoandrade.delivery.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CrudProdutoService crudProdutoService;

    @Autowired
    private ProdutoFullModelAssembler produtoFullModelAssembler;

    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ProdutoFullModel buscarPorId(@PathVariable String id) {
        Produto produto = crudProdutoService.buscarPorId(id);

        return produtoFullModelAssembler.toModel(produto);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProdutoFullModel inserir(@Valid @RequestBody ProdutoInput produtoInput) {
        try {
            Produto produto = crudProdutoService.inserir(produtoInputDisassembler.toDomainModel(produtoInput));

            return produtoFullModelAssembler.toModel(produto);
        } catch (CategoriaNaoEncontradaException e) {
            throw new SistemaException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ProdutoFullModel atualizar(@Valid @RequestBody ProdutoInput produtoInput, @PathVariable String id) {
        try {
            Produto produto = crudProdutoService.buscarPorId(id);
            produtoInputDisassembler.copyToDomainModel(produtoInput, produto);
            produto = crudProdutoService.atualizar(produto);

            return produtoFullModelAssembler.toModel(produto);
        } catch (CategoriaNaoEncontradaException e) {
            throw new SistemaException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable String id){
        crudProdutoService.deletarPorId(id);
    }

    @PutMapping("{id}/quantidade-estoque")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adicionarQuantidadeEstoque(@Valid @RequestBody ProdutoEstoqueInput produtoEstoqueInput, @PathVariable String id){
        produtoService.adicionarQuantidadeEstoque(produtoEstoqueInput.getQuantidade(), id);
    }

    @DeleteMapping("/{id}/quantidade-estoque")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removerQuantidadeEstoque(@Valid @RequestBody ProdutoEstoqueInput produtoEstoqueInput, @PathVariable String id){
        produtoService.removerQuantidadeEstoque(produtoEstoqueInput.getQuantidade(), id);
    }

}
