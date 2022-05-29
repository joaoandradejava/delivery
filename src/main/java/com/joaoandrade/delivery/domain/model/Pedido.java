package com.joaoandrade.delivery.domain.model;

import com.joaoandrade.delivery.domain.model.enumeration.FormaPagamento;
import com.joaoandrade.delivery.domain.model.enumeration.StatusPedido;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime data;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataConcluido;

    @Enumerated(EnumType.STRING)

    private StatusPedido status;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne
    private Endereco enderecoDeEntrega;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "id.pedido")
    private List<ItemPedido> itens = new ArrayList<>();


    public Pedido() {
    }

    public Pedido(Long id, LocalDateTime data, LocalDateTime dataCancelamento, LocalDateTime dataConcluido, StatusPedido status, FormaPagamento formaPagamento, Endereco enderecoDeEntrega, Cliente cliente) {
        this.id = id;
        this.data = data;
        this.dataCancelamento = dataCancelamento;
        this.dataConcluido = dataConcluido;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.enderecoDeEntrega = enderecoDeEntrega;
        this.cliente = cliente;
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

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

