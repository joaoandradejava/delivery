package com.joaoandrade.delivery.domain.repository;

import com.joaoandrade.delivery.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("select e from Endereco e where e.cliente.id = :clienteId and e.id = :enderecoId")
    Optional<Endereco> buscarEnderecoDoCliente(Long clienteId, Long enderecoId);
}
