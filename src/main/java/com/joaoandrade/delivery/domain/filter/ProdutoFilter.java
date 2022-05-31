package com.joaoandrade.delivery.domain.filter;

public class ProdutoFilter {
    private String nome;
    private boolean isTemDesconto;
    private boolean isTemEstoque;
    private Long categoriaId;

    public ProdutoFilter() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isTemDesconto() {
        return isTemDesconto;
    }

    public void setTemDesconto(boolean temDesconto) {
        isTemDesconto = temDesconto;
    }

    public boolean isTemEstoque() {
        return isTemEstoque;
    }

    public void setTemEstoque(boolean temEstoque) {
        isTemEstoque = temEstoque;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
