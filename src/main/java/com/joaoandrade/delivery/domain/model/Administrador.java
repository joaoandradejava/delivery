package com.joaoandrade.delivery.domain.model;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    public Administrador(Long id, String nome, String email, String senha) {
        super(id, nome, email, senha);
    }
}
