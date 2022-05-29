package com.joaoandrade.delivery.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
    private Integer porcentagemDesconto;
    private Boolean isTemDesconto = Boolean.FALSE;
    private Boolean isTemEstoque = Boolean.FALSE;

    @ManyToOne
    private CategoriaProduto categoria;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private ImagemProduto imagem;

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Integer porcentagemDesconto, Boolean isTemDesconto, Boolean isTemEstoque, CategoriaProduto categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.porcentagemDesconto = porcentagemDesconto;
        this.isTemDesconto = isTemDesconto;
        this.isTemEstoque = isTemEstoque;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }

    public ImagemProduto getImagem() {
        return imagem;
    }

    public void setImagem(ImagemProduto imagem) {
        this.imagem = imagem;
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
