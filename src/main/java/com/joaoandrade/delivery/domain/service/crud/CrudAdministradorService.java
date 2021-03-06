package com.joaoandrade.delivery.domain.service.crud;

import com.joaoandrade.delivery.domain.model.Administrador;
import com.joaoandrade.delivery.domain.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudAdministradorService {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Administrador inserir(Administrador administrador) {
        administrador.setSenha(passwordEncoder.encode(administrador.getSenha()));

        return repository.save(administrador);
    }
}
