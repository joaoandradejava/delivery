package com.joaoandrade.delivery.infrastructure.specification;

import com.joaoandrade.delivery.domain.filter.ProdutoClienteFilter;
import com.joaoandrade.delivery.domain.filter.ProdutoFilter;
import com.joaoandrade.delivery.domain.model.Produto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProdutoSpecification {

    public static Specification<Produto> buscarParaOsClientes(ProdutoClienteFilter produtoClienteFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isTemEstoque"), true));

            if (produtoClienteFilter.getTemDesconto() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isTemDesconto"), produtoClienteFilter.getTemDesconto()));
            }

            if (StringUtils.hasLength(produtoClienteFilter.getNome())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), '%' + produtoClienteFilter.getNome().toUpperCase() + "%"));
            }

            if (produtoClienteFilter.getCategoriaId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("categoria").get("id"), produtoClienteFilter.getCategoriaId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Produto> buscarTodos(ProdutoFilter produtoFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (produtoFilter.getTemEstoque() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isTemEstoque"), produtoFilter.getTemEstoque()));
            }

            if (produtoFilter.getTemDesconto() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isTemDesconto"), produtoFilter.getTemDesconto()));
            }

            if (StringUtils.hasLength(produtoFilter.getNome())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), '%' + produtoFilter.getNome().toUpperCase() + "%"));
            }

            if (produtoFilter.getCategoriaId() != null) {
                predicates.add(criteriaBuilder.equal(root.join("categoria").get("id"), produtoFilter.getCategoriaId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
