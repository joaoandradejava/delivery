package com.joaoandrade.delivery.api.input;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteUpdateInput {

    @Size(min = 3, max = 255)
    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;


    @Size(max = 11)
    @NotBlank
    private String telefone;

    @Size(max = 11)
    @CPF
    private String cpf;

    public ClienteUpdateInput() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
