package com.joaoandrade.delivery.domain.repository;

import com.joaoandrade.delivery.domain.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}
