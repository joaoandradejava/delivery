package com.joaoandrade.delivery.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaInput {

    @NotBlank
    @Size(min = 3, max = 255)
    private String nome;

    public CategoriaInput() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
