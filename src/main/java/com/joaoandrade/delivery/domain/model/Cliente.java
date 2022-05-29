package com.joaoandrade.delivery.domain.model;

import com.joaoandrade.delivery.domain.model.enumeration.PerfilUsuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "cliente_cpf_unique", columnNames = "cpf")})
public class Cliente extends Usuario {
    private String telefone;

    private String cpf;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
        adicionarPerfil(PerfilUsuario.CLIENTE);
    }

    public Cliente(Long id, String nome, String email, String senha, String telefone) {
        super(id, nome, email, senha);
        this.telefone = telefone;
        adicionarPerfil(PerfilUsuario.CLIENTE);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


}
