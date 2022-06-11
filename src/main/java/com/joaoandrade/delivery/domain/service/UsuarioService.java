package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.Usuario;
import com.joaoandrade.delivery.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario buscarPorId(Long id) {
        String[] args = {"Usuario", id.toString()};
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(messageSource.getMessage("entidade.nao.encontrada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
    }

    @Transactional
    public void mudarSenha(String senhaAtual, String novaSenha, String confirmacaoNovaSenha, Long id) {
        Usuario usuario = buscarPorId(id);

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new SistemaException("A senha Atual informada não corresponde a sua senha.");
        }

        if (!novaSenha.equals(confirmacaoNovaSenha)) {
            throw new SistemaException("A confirmação da nova senha tem que ser igual a nova senha informada.");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
    }
}
