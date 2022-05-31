package com.joaoandrade.delivery.domain.repository;

import com.joaoandrade.delivery.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>, JpaSpecificationExecutor<Produto> {
}
