package com.joaoandrade.delivery.infrastructure.utility;

import com.joaoandrade.delivery.domain.exception.ErroInternoNoServidorException;

public enum ConstraintUniqueUtility {
    CATEGORIA_NOME_UNIQUE("Já existe uma categoria com este nome.");

    private String descricao;

    ConstraintUniqueUtility(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ConstraintUniqueUtility toConstraint(String value) {
        for (ConstraintUniqueUtility constraint : ConstraintUniqueUtility.values()) {
            if (constraint.name().equalsIgnoreCase(value)) {
                return constraint;
            }
        }

        return null;
    }
}
