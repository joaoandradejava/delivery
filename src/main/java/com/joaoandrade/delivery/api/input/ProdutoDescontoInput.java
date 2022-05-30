package com.joaoandrade.delivery.api.input;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoDescontoInput {

    @Max(value = 100)
    @Positive
    @NotNull
    private Integer porcentagemDesconto;

    public ProdutoDescontoInput() {
    }

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Integer porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }
}
