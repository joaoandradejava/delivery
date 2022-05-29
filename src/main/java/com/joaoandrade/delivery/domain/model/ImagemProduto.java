package com.joaoandrade.delivery.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagem;

    @OneToOne
    private Produto produto;

    public ImagemProduto() {
    }

    public ImagemProduto(Long id, String imagem, Produto produto) {
        this.id = id;
        this.imagem = imagem;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
