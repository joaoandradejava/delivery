package com.joaoandrade.delivery.domain.repository;

import com.joaoandrade.delivery.domain.model.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
}
