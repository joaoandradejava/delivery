package com.joaoandrade.delivery.infrastructure.utility;

public enum ContentTypeImage {
    PNG("image/png"), JPG("image/jpg");

    private String descricao;

    ContentTypeImage(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static boolean isImagem(String contentType) {
        for (ContentTypeImage type : ContentTypeImage.values()) {
            if (type.getDescricao().equalsIgnoreCase(contentType)) {
                return true;
            }
        }

        return false;
    }
}
