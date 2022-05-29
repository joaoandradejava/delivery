package com.joaoandrade.delivery.domain.model.enumeration;

public enum FormaPagamento {
    DINHEIRO("Dinheiro"), CARTAO_CREDITO_DEBITO("Cartão de credito/debito"), PIX("PIX");

    private String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
