package com.joaoandrade.delivery.core.emailconfig;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email")
public class EmailConfigProperty {

    private String email;
    private String senha;
    private TipoEnvioEmail tipoEnvioEmail;
    private String emailSendbox;

    public EmailConfigProperty() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoEnvioEmail getTipoEnvioEmail() {
        return tipoEnvioEmail;
    }

    public void setTipoEnvioEmail(TipoEnvioEmail tipoEnvioEmail) {
        this.tipoEnvioEmail = tipoEnvioEmail;
    }

    public String getEmailSendbox() {
        return emailSendbox;
    }

    public void setEmailSendbox(String emailSendbox) {
        this.emailSendbox = emailSendbox;
    }
}
