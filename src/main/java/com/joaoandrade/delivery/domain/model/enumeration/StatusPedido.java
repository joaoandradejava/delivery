package com.joaoandrade.delivery.domain.model.enumeration;

public enum StatusPedido {
    AGUARDANDO_CONFIRMACAO("Aguardando confirmação"), PREPARANDO_PEDIDO("Preparando pedido"), SAIU_PARA_ENTREGA("Saiu para entregaa"), ENTREGUE("Entregue"), CANCELADO("Cancelado");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
