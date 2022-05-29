package com.joaoandrade.delivery.api.model;

import java.math.BigDecimal;

public class ProdutoModel {
    private String id;
    private String nome;
    private String descricaoResumida;
    private BigDecimal preco;
    private ImagemProdutoModel imagem;
    private Boolean isTemDesconto;
    private Integer porcentagemDesconto;

    public ProdutoModel() {
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

    public ImagemProdutoModel getImagem() {
        return imagem;
    }

    public void setImagem(ImagemProdutoModel imagem) {
        this.imagem = imagem;
    }

    public Boolean getTemDesconto() {
        return isTemDesconto;
    }

    public void setTemDesconto(Boolean temDesconto) {
        isTemDesconto = temDesconto;
    }

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Integer porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }
}
