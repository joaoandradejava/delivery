package com.joaoandrade.delivery.api.exceptionhandler;

public enum Error {
    SISTEMA_EXCEPTION("sistema-exception", "Erro do lado do client"),
    ENTIDADE_NAO_ENCONTRADA_EXCEPTION("entidade-nao-encontrada-exception", "Entidade não encontrada"),
    ERROR_VALIDACAO_DADOS("error-validacao-dados", "Erro de validação dos dados"),

    ERROR_DESSERIALIZACAO_JSON("erro-desserializacao-json", "Ocorreu um erro na desserialização do JSON"),
    PROPRIEDADE_INEXISTENTE("propriedade-inexistente", "Propriedade inexistente"),

    METODO_SOLICITACAO_NAO_SUPORTADO("metodo-solicitacao-nao-suportado", "Metodo de solicitação não suportado"),

    ENDPOINT_INEXISTENTE("endpoint-inexistente", "Endpoint inexistente"),
    ERRO_INTERNO_NO_SERVIDOR("erro-interno-no-servidor", "Erro interno no servidor"),
    TAMANHO_MAXIMO_UPLOAD_EXCEDIDO("tamanho-maximo-upload-excedido", "Tamanho máximo de upload excedido"),
    FALHA_NA_AUTENTICACAO("falha-na-autenticacao", "Falha na autenticação"),
    ACESSO_NEGADO("acesso-negado", "Acesso negado");

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
