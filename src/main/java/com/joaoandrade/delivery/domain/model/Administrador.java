package com.joaoandrade.delivery.domain.model;

import com.joaoandrade.delivery.domain.model.enumeration.PerfilUsuario;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    public Administrador() {
        super.adicionarPerfil(PerfilUsuario.ADMINISTRADOR);
    }

    public Administrador(Long id, String nome, String email, String senha) {
        super(id, nome, email, senha);
        super.adicionarPerfil(PerfilUsuario.ADMINISTRADOR);
    }
}
