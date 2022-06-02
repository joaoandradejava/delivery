package com.joaoandrade.delivery.domain.repository;

import com.joaoandrade.delivery.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p where p.cliente.id = :clienteId")
    Page<Pedido> buscarTodos(Long clienteId, Pageable pageable);

    @Query("select p from Pedido p where p.cliente.id = :clienteId and p.id = :pedidoId")
    Optional<Pedido> buscarPedidoDoCliente(Long clienteId, Long pedidoId);
}
