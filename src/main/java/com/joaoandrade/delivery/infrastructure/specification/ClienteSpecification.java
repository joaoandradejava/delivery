package com.joaoandrade.delivery.infrastructure.specification;

import com.joaoandrade.delivery.domain.filter.ClienteFilter;
import com.joaoandrade.delivery.domain.model.Cliente;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ClienteSpecification {

    public static Specification<Cliente> buscarTodos(ClienteFilter clienteFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(clienteFilter.getNome())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), "%" + clienteFilter.getNome().toUpperCase() + "%"));
            }

            if (StringUtils.hasLength(clienteFilter.getEmail())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("email")), "%" + clienteFilter.getEmail().toUpperCase() + "%"));
            }

            if (StringUtils.hasLength(clienteFilter.getCpf())) {
                predicates.add(criteriaBuilder.like(root.get("cpf"), "%" + clienteFilter.getCpf() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
