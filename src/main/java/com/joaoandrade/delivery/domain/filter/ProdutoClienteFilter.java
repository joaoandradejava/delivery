package com.joaoandrade.delivery.domain.filter;

public class ProdutoClienteFilter {
    private String nome;
    private Boolean isTemDesconto;
    private Long categoriaId;

    public ProdutoClienteFilter() {
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
