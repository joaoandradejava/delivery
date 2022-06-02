package com.joaoandrade.delivery.api.model;

import com.joaoandrade.delivery.domain.model.enumeration.FormaPagamento;
import com.joaoandrade.delivery.domain.model.enumeration.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoModel {
    private Long id;
    private LocalDateTime data;
    private BigDecimal valorTotal;
    private List<ItemPedidoModel> itens = new ArrayList<>();

    public PedidoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedidoModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoModel> itens) {
        this.itens = itens;
    }
}
