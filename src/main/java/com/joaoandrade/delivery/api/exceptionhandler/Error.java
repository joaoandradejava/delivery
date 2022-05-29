package com.joaoandrade.delivery.api.exceptionhandler;

public enum Error {
    NEGOCIO_EXCEPTION("negocio-exception", "Erro do lado do client"),
    ENTIDADE_NAO_ENCONTRADA_EXCEPTION("entidade-nao-encontrada-exception", "Entidade não encontrada"),
    ERROR_VALIDACAO_DADOS("error-validacao-dados", "Erro de validação dos dados");

    private String type;
    private String title;

    Error(String type, String title) {
        this.type = "https://www.joaoandradejava.com.br/" + type;
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
