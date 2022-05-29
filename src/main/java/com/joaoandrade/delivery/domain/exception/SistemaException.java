package com.joaoandrade.delivery.domain.exception;

public class SistemaException extends RuntimeException {

    public SistemaException(String mensagem) {
        super(mensagem);
    }
}
