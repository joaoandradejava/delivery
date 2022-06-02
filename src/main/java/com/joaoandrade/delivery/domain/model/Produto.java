package com.joaoandrade.delivery.domain.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Produto {

    @Id
    private String id;
    private String nome;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "org.hibernate.type.TextType")
    private String descricao;

    private String descricaoResumida;
    private BigDecimal preco;
    private BigDecimal precoAtual;
    private Integer quantidadeEstoque = 0;
    private Integer porcentagemDesconto = 0;
    private Boolean isTemDesconto = Boolean.FALSE;
    private Boolean isTemEstoque = Boolean.FALSE;

    @CreationTimestamp
    private LocalDateTime dataCadastro;

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
        atribuirPrecoAtual();
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
        atribuirPrecoAtual();
    }

    public BigDecimal getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(BigDecimal precoAtual) {
        this.precoAtual = precoAtual;
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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
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
        this.quantidadeEstoque -= quantidade;
        if (this.quantidadeEstoque <= 0) {
            this.quantidadeEstoque = 0;
            this.isTemEstoque = Boolean.FALSE;
        }
    }

    public void adicionarDesconto(Integer porcentagemDesconto) {
        if (isTemDesconto) {
            return;
        }

        this.porcentagemDesconto = porcentagemDesconto;
        this.isTemDesconto = Boolean.TRUE;
        atribuirPrecoAtual();
    }

    public void removerDesconto() {
        this.porcentagemDesconto = 0;
        this.isTemDesconto = Boolean.FALSE;
        atribuirPrecoAtual();
    }

    public void atribuirPrecoAtual() {
        if (!this.isTemDesconto) {
            this.precoAtual = preco;
            return;
        }

        this.precoAtual = preco.multiply(new BigDecimal(1 - (porcentagemDesconto / 100.0)));
    }

    public boolean verificarDisponibilidadeEstoque(int quantidade) {
        return this.quantidadeEstoque >= quantidade ? true : false;
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
