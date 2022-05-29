package com.joaoandrade.delivery.domain.model.pk;

import com.joaoandrade.delivery.domain.model.Pedido;
import com.joaoandrade.delivery.domain.model.Produto;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoPk implements Serializable {

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

    public ItemPedidoPk() {
    }

    public ItemPedidoPk(Produto produto, Pedido pedido) {
        this.produto = produto;
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPk that = (ItemPedidoPk) o;
        return produto.equals(that.produto) && pedido.equals(that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, pedido);
    }
}
