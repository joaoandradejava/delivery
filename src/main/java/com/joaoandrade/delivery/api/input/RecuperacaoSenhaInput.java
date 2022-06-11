package com.joaoandrade.delivery.api.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RecuperacaoSenhaInput {

    @Email
    @NotBlank
    private String email;

    public RecuperacaoSenhaInput() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
