package com.joaoandrade.delivery.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    private String id;
    private String nome;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String descricao;

    private String descricaoResumida;
    private BigDecimal preco;
    private Integer quantidadeEstoque = 0;
    private Integer porcentagemDesconto = 0;
    private Boolean isTemDesconto = Boolean.FALSE;
    private Boolean isTemEstoque = Boolean.FALSE;

    @ManyToOne
    private Categoria categoria;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private ImagemProduto imagem;

    public Produto() {
    }

    public Produto(String id, String nome, String descricao, String descricaoResumida, BigDecimal preco, Integer quantidadeEstoque, Integer porcentagemDesconto, Boolean isTemDesconto, Boolean isTemEstoque, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.descricaoResumida = descricaoResumida;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.porcentagemDesconto = porcentagemDesconto;
        this.isTemDesconto = isTemDesconto;
        this.isTemEstoque = isTemEstoque;
        this.categoria = categoria;
    }

    @PrePersist
    public void gerarUUID() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Integer porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public Boolean getTemDesconto() {
        return isTemDesconto;
    }

    public void setTemDesconto(Boolean temDesconto) {
        isTemDesconto = temDesconto;
    }

    public Boolean getTemEstoque() {
        return isTemEstoque;
    }

    public void setTemEstoque(Boolean temEstoque) {
        isTemEstoque = temEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ImagemProduto getImagem() {
        return imagem;
    }

    public void setImagem(ImagemProduto imagem) {
        this.imagem = imagem;
    }

    public void adicionarQuantidadeEstoque(Integer quantidade) {
        this.quantidadeEstoque += quantidade;
        if (quantidadeEstoque > 0) {
            this.isTemEstoque = Boolean.TRUE;
        }
    }

    public void removerQuantidadeEstoque(Integer quantidade) {
        this.quantidadeEstoque = quantidade > this.quantidadeEstoque ? 0 : this.quantidadeEstoque--;
        if (this.quantidadeEstoque == 0) {
            this.isTemEstoque = Boolean.FALSE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
