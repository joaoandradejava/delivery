package com.joaoandrade.delivery.domain.model.enumeration;

public enum PerfilUsuario {
    ADMINISTRADOR("ROLE_ADMINISTRADOR"), CLIENTE("ROLE_CLIENTE");

    private String descricao;

    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
