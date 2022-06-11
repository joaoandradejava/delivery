package com.joaoandrade.delivery.api.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioMudarSenhaInput {

    @Size(min = 6, max = 255)
    @NotBlank
    private String senhaAtual;

    @Size(min = 6, max = 255)
    @NotBlank
    private String novaSenha;

    @Size(min = 6, max = 255)
    @NotBlank
    private String confirmacaoNovaSenha;

    public UsuarioMudarSenhaInput() {
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmacaoNovaSenha() {
        return confirmacaoNovaSenha;
    }

    public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
        this.confirmacaoNovaSenha = confirmacaoNovaSenha;
    }
}
