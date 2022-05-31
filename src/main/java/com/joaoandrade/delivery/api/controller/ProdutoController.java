package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.ProdutoFullModelAssembler;
import com.joaoandrade.delivery.api.assembler.ProdutoModelAssembler;
import com.joaoandrade.delivery.api.disassembler.ProdutoInputDisassembler;
import com.joaoandrade.delivery.api.input.ProdutoDescontoInput;
import com.joaoandrade.delivery.api.input.ProdutoEstoqueInput;
import com.joaoandrade.delivery.api.input.ProdutoInput;
import com.joaoandrade.delivery.api.model.ProdutoFullModel;
import com.joaoandrade.delivery.api.model.ProdutoModel;
import com.joaoandrade.delivery.domain.exception.CategoriaNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.ErroInternoNoServidorException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.filter.ProdutoClienteFilter;
import com.joaoandrade.delivery.domain.filter.ProdutoFilter;
import com.joaoandrade.delivery.domain.model.Produto;
import com.joaoandrade.delivery.domain.service.CrudProdutoService;
import com.joaoandrade.delivery.domain.service.ProdutoService;
import com.joaoandrade.delivery.infrastructure.utility.ContentTypeImage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @GetMapping
    public Page<ProdutoModel> buscarTodos(ProdutoFilter produtoFilter, @PageableDefault(sort = "dataCadastro") Pageable pageable) {
        Page<Produto> page = crudProdutoService.buscarTodos(produtoFilter, pageable);

        return page.map(produto -> produtoModelAssembler.toModel(produto));
    }

    @GetMapping("/disponivel")
    public Page<ProdutoModel> buscarProdutosDisponiveis(ProdutoClienteFilter produtoClienteFilter, @PageableDefault(sort = "dataCadastro") Pageable pageable) {
        Page<Produto> page = crudProdutoService.buscarProdutosDisponiveis(produtoClienteFilter, pageable);

        return page.map(produto -> produtoModelAssembler.toModel(produto));
    }

    @GetMapping("/{id}")
    public ProdutoFullModel buscarPorId(@PathVariable String id) {
        Produto produto = crudProdutoService.buscarPorId(id);

        return produtoFullModelAssembler.toModel(produto);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
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

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
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

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable String id) {
        crudProdutoService.deletarPorId(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @PutMapping("{id}/quantidade-estoque")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adicionarQuantidadeEstoque(@Valid @RequestBody ProdutoEstoqueInput produtoEstoqueInput, @PathVariable String id) {
        produtoService.adicionarQuantidadeEstoque(produtoEstoqueInput.getQuantidade(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}/quantidade-estoque")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removerQuantidadeEstoque(@Valid @RequestBody ProdutoEstoqueInput produtoEstoqueInput, @PathVariable String id) {
        produtoService.removerQuantidadeEstoque(produtoEstoqueInput.getQuantidade(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @PutMapping("/{id}/desconto")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adicionarDesconto(@Valid @RequestBody ProdutoDescontoInput produtoDescontoInput, @PathVariable String id) {
        produtoService.adicionarDesconto(produtoDescontoInput.getPorcentagemDesconto(), id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}/desconto")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removerDesconto(@PathVariable String id) {
        produtoService.removerDesconto(id);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @PutMapping("/{id}/imagem")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adicionarImagem(MultipartFile foto, @PathVariable String id) {
        if (foto == null) {
            return;
        }

        if (!ContentTypeImage.isImagem(foto.getContentType())) {
            throw new SistemaException("O arquivo tem que ser uma imagem!.");
        }

        try {
            byte[] conteudo = foto.getBytes();
            produtoService.adicionarImagem(conteudo, id);
        } catch (IOException e) {
            throw new ErroInternoNoServidorException("Ocorreu um erro inesperado na hora de salvar a imagem do produto.");

        }

    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}/imagem")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removerImagem(@PathVariable String id) {
        produtoService.removerImagem(id);
    }


}
