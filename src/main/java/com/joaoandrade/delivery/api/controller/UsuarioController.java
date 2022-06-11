package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.input.UsuarioMudarSenhaInput;
import com.joaoandrade.delivery.core.security.UsuarioAutenticado;
import com.joaoandrade.delivery.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/senha")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void mudarSenha(@Valid @RequestBody UsuarioMudarSenhaInput usuarioMudarSenhaInput, @AuthenticationPrincipal UsuarioAutenticado usuarioAutenticado) {
        usuarioService.mudarSenha(usuarioMudarSenhaInput.getSenhaAtual(), usuarioMudarSenhaInput.getNovaSenha(), usuarioMudarSenhaInput.getConfirmacaoNovaSenha(), usuarioAutenticado.getId());
    }
}
