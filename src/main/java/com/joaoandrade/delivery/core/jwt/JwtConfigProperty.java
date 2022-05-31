package com.joaoandrade.delivery.core.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("jwt")
@Component
public class JwtConfigProperty {

    /**
     * Senha do token jwt
     */
    private String senhaToken;

    /**
     * Senha do token jwt
     */
    private Long tempoDeExpiracaoTokenJwt;

    public JwtConfigProperty() {
    }

    public String getSenhaToken() {
        return senhaToken;
    }

    public void setSenhaToken(String senhaToken) {
        this.senhaToken = senhaToken;
    }

    public Long getTempoDeExpiracaoTokenJwt() {
        return tempoDeExpiracaoTokenJwt;
    }

    public void setTempoDeExpiracaoTokenJwt(Long tempoDeExpiracaoTokenJwt) {
        this.tempoDeExpiracaoTokenJwt = tempoDeExpiracaoTokenJwt;
    }
}
