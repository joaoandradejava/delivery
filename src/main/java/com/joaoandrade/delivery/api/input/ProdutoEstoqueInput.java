package com.joaoandrade.delivery.api.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoEstoqueInput {

    @Positive
    @NotNull
    private Integer quantidade;

    public ProdutoEstoqueInput() {
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
