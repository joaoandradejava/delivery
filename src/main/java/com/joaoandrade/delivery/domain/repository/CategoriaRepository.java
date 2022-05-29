package com.joaoandrade.delivery.domain.repository;

import com.joaoandrade.delivery.domain.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("select c from Categoria c where lower(c.nome) like lower(concat('%', :nome, '%'))")
    Page<Categoria> buscarTodosPorNome(String nome, Pageable pageable);
}
