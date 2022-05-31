package com.joaoandrade.delivery.api.input;

import com.joaoandrade.delivery.domain.model.*;
import com.joaoandrade.delivery.domain.model.enumeration.FormaPagamento;
import com.joaoandrade.delivery.domain.model.enumeration.StatusPedido;
import com.joaoandrade.delivery.domain.model.pk.ItemPedidoPk;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PedidoCreateInput {

    @Valid
    @NotEmpty
    private Set<ItemPedidoInput> itens = new HashSet<>();

    @NotNull
    private FormaPagamento formaPagamento;

    @NotNull
    private Long enderecoDeEntregaId;

    public PedidoCreateInput() {
    }

    public Set<ItemPedidoInput> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedidoInput> itens) {
        this.itens = itens;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Long getEnderecoDeEntregaId() {
        return enderecoDeEntregaId;
    }

    public void setEnderecoDeEntregaId(Long enderecoDeEntregaId) {
        this.enderecoDeEntregaId = enderecoDeEntregaId;
    }

    public Pedido toDomainModel() {
        Endereco enderecoDeEntrega = new Endereco();
        enderecoDeEntrega.setId(enderecoDeEntregaId);
        Pedido pedido = new Pedido(null, null, null, null, StatusPedido.AGUARDANDO_CONFIRMACAO, formaPagamento, enderecoDeEntrega, null);
        for (ItemPedidoInput item : this.itens) {
            ItemPedidoPk itemPedidoPk = new ItemPedidoPk();
            Produto produto = new Produto();
            produto.setId(item.getProdutoId());
            itemPedidoPk.setProduto(produto);
            ItemPedido itemPedido = new ItemPedido(itemPedidoPk, item.getQuantidade(), null, null);

            pedido.getItens().add(itemPedido);
        }

        return pedido;
    }
}
