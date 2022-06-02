package com.joaoandrade.delivery.domain.model;


import com.joaoandrade.delivery.domain.model.pk.ItemPedidoPk;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoPk id;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    private BigDecimal valorTotal = BigDecimal.ZERO;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoPk id, Integer quantidade, BigDecimal precoUnitario) {
        this.id = id;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public ItemPedidoPk getId() {
        return id;
    }

    public void setId(ItemPedidoPk id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void calcularValorTotal() {
        this.valorTotal = this.valorTotal.add(this.precoUnitario.multiply(new BigDecimal(this.quantidade)));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
