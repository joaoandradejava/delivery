package com.joaoandrade.delivery.domain.service.email;

import com.joaoandrade.delivery.domain.model.Usuario;

public interface EnvioEmailService {
    public void enviarEmail(String titulo, String corpo, Usuario email);
}
