package com.joaoandrade.delivery.api.model;

public class ImagemProdutoModel {
    private Long id;
    private byte[] imagem;

    public ImagemProdutoModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
