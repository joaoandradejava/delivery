package com.joaoandrade.delivery.domain.listener;


import com.joaoandrade.delivery.domain.model.Usuario;
import com.joaoandrade.delivery.domain.observer.UsuarioEsqueceuSenhaObserver;
import com.joaoandrade.delivery.domain.service.email.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnvioEmailListener {

    @Autowired
    private EnvioEmailService envioEmailService;

    @EventListener
    public void usuarioEsqueceuSenhaListener(UsuarioEsqueceuSenhaObserver usuarioEsqueceuSenhaObserver) {
        Usuario usuario = usuarioEsqueceuSenhaObserver.getUsuario();
        usuario.setSenha(usuarioEsqueceuSenhaObserver.getNovaSenha());
        envioEmailService.enviarEmail("Recuperação da senha", "recuperacao-senha.html", usuario);
    }
}
