package com.joaoandrade.delivery.infrastructure.service;

import com.joaoandrade.delivery.domain.model.Usuario;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EnvioEmailServiceImpl extends AbstractEnvioEmailService {

    @Override
    public void enviarEmail(String titulo, String corpo, Usuario usuario) {
        MimeMessage mimeMessage = getMimeMessage(titulo, corpo, usuario);
        javaMailSender.send(mimeMessage);
    }
}
