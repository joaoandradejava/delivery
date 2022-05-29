package com.joaoandrade.delivery.api.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProdutoInput {

    @Size(min = 2, max = 255)
    @NotBlank
    private String nome;
    private String descricao;

    @Size(max = 255)
    private String descricaoResumida;

    @PositiveOrZero
    @NotNull
    private BigDecimal preco;

    @Valid
    @NotNull
    private CategoriaIdInput categoria;

    public ProdutoInput() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoResumida() {
        return descricaoResumida;
    }

    public void setDescricaoResumida(String descricaoResumida) {
        this.descricaoResumida = descricaoResumida;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public CategoriaIdInput getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaIdInput categoria) {
        this.categoria = categoria;
    }
}
