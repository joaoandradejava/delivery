package com.joaoandrade.delivery.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProdutoFullModel {
    private String id;
    private String nome;
    private String descricao;
    private String descricaoResumida;
    private BigDecimal preco;
    private BigDecimal precoAtual;
    private Boolean isTemDesconto;
    private Boolean isTemEstoque;
    private Integer quantidadeEstoque;
    private Integer porcentagemDesconto;

    private LocalDateTime dataCadastro;
    private ImagemProdutoModel imagem;
    private CategoriaModel categoria;

    public ProdutoFullModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoResumida() {
        return descricaoResumida;
    }

    public void setDescricaoResumida(String descricaoResumida) {
        this.descricaoResumida = descricaoResumida;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(BigDecimal precoAtual) {
        this.precoAtual = precoAtual;
    }

    public Boolean getTemDesconto() {
        return isTemDesconto;
    }

    public void setTemDesconto(Boolean temDesconto) {
        isTemDesconto = temDesconto;
    }

    public Boolean getTemEstoque() {
        return isTemEstoque;
    }

    public void setTemEstoque(Boolean temEstoque) {
        isTemEstoque = temEstoque;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Integer porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public ImagemProdutoModel getImagem() {
        return imagem;
    }

    public void setImagem(ImagemProdutoModel imagem) {
        this.imagem = imagem;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }
}
