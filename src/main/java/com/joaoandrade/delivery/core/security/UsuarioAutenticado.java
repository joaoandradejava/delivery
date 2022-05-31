package com.joaoandrade.delivery.core.security;

import com.joaoandrade.delivery.domain.model.enumeration.PerfilUsuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioAutenticado implements UserDetails {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private List<? extends GrantedAuthority> authorities;
    private boolean isAdmin;

    public UsuarioAutenticado(Long id, String nome, String email, String senha, Set<PerfilUsuario> perfis) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.authorities = perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getDescricao())).collect(Collectors.toList());
        this.isAdmin = perfis.contains(PerfilUsuario.ADMINISTRADOR);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAdmin() {
        return isAdmin;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
