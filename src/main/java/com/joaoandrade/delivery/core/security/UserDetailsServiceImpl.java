package com.joaoandrade.delivery.core.security;

import com.joaoandrade.delivery.domain.model.Usuario;
import com.joaoandrade.delivery.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new UsuarioAutenticado(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
    }
}
