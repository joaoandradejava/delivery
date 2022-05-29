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
    private Integer porcentagemDesconto;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoPk id, Integer quantidade, BigDecimal precoUnitario, Integer porcentagemDesconto) {
        this.id = id;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.porcentagemDesconto = porcentagemDesconto;
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

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Integer porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
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
