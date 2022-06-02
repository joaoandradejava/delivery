package com.joaoandrade.delivery.domain.filter;

public class ProdutoFilter {
    private String nome;
    private Boolean isTemDesconto;
    private Boolean isTemEstoque;
    private Long categoriaId;

    public ProdutoFilter() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
