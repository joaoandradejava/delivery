package com.joaoandrade.delivery.api.model;

import com.joaoandrade.delivery.domain.model.*;
import com.joaoandrade.delivery.domain.model.enumeration.FormaPagamento;
import com.joaoandrade.delivery.domain.model.enumeration.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoFullModel {
    private Long id;
    private LocalDateTime data;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataConcluido;
    private StatusPedido status;
    private FormaPagamento formaPagamento;
    private BigDecimal valorTotal;
    private EnderecoModel enderecoDeEntrega;
    private ClienteModel cliente;
    private List<ItemPedidoModel> itens = new ArrayList<>();

    public PedidoFullModel() {
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

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public LocalDateTime getDataConcluido() {
        return dataConcluido;
    }

    public void setDataConcluido(LocalDateTime dataConcluido) {
        this.dataConcluido = dataConcluido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public EnderecoModel getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(EnderecoModel enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedidoModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoModel> itens) {
        this.itens = itens;
    }

    public static PedidoFullModel toModel(Pedido pedido) {
        PedidoFullModel pedidoFullModel = new PedidoFullModel();
        pedidoFullModel.setId(pedido.getId());
        pedidoFullModel.setData(pedido.getData());
        pedidoFullModel.setDataCancelamento(pedido.getDataCancelamento());
        pedidoFullModel.setDataConcluido(pedido.getDataConcluido());
        pedidoFullModel.setStatus(pedido.getStatus());
        pedidoFullModel.setFormaPagamento(pedido.getFormaPagamento());
        pedidoFullModel.setValorTotal(pedido.getValorTotal());
        Endereco endereco = pedido.getEnderecoDeEntrega();
        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setId(endereco.getId());
        enderecoModel.setCep(endereco.getCep());
        enderecoModel.setRua(endereco.getRua());
        enderecoModel.setBairro(endereco.getBairro());
        enderecoModel.setCidade(endereco.getCidade());
        enderecoModel.setComplemento(endereco.getComplemento());
        enderecoModel.setEstado(endereco.getEstado());
        enderecoModel.setNumero(endereco.getNumero());
        pedidoFullModel.setEnderecoDeEntrega(enderecoModel);

        Cliente cliente1 = pedido.getCliente();
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setId(cliente1.getId());
        clienteModel.setNome(cliente1.getNome());
        clienteModel.setCpf(cliente1.getCpf());
        clienteModel.setEmail(cliente1.getEmail());
        clienteModel.setTelefone(cliente1.getTelefone());
        pedidoFullModel.setCliente(clienteModel);

        pedidoFullModel.setItens(new ArrayList<>());

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getId().getProduto();
            ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
            itemPedidoModel.setId(produto.getId());
            itemPedidoModel.setNome(produto.getNome());
            itemPedidoModel.setQuantidade(item.getQuantidade());
            itemPedidoModel.setPrecoUnitario(item.getPrecoUnitario());
            itemPedidoModel.setValorTotal(item.getValorTotal());

            pedidoFullModel.getItens().add(itemPedidoModel);
        }

        return pedidoFullModel;
    }
}
