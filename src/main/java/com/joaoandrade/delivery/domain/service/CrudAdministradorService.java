package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.model.Administrador;
import com.joaoandrade.delivery.domain.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudAdministradorService {

    @Autowired
    private AdministradorRepository repository;

    @Transactional
    public Administrador inserir(Administrador administrador) {
        return repository.save(administrador);
    }
}
