package com.joaoandrade.delivery.api.model;

public class ImagemProdutoModel {
    private Long id;
    private String imagem;

    public ImagemProdutoModel() {
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
}
