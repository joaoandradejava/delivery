package com.joaoandrade.delivery.infrastructure.service;

import com.joaoandrade.delivery.core.emailconfig.EmailConfigProperty;
import com.joaoandrade.delivery.core.emailconfig.TipoEnvioEmail;
import com.joaoandrade.delivery.domain.exception.ErroInternoNoServidorException;
import com.joaoandrade.delivery.domain.model.Usuario;
import com.joaoandrade.delivery.domain.service.email.EnvioEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEnvioEmailService implements EnvioEmailService {

    @Autowired
    protected JavaMailSender javaMailSender;

    @Autowired
    protected EmailConfigProperty emailConfigProperty;

    @Autowired
    private Configuration configuration;

    protected MimeMessage getMimeMessage(String titulo, String corpo, Usuario usuario) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.setSubject(titulo);
            mimeMessageHelper.setText(prepararHtml(corpo, usuario), true);
            mimeMessageHelper.setTo(emailConfigProperty.getTipoEnvioEmail() == null || emailConfigProperty.getTipoEnvioEmail() == TipoEnvioEmail.SMTP ? usuario.getEmail() : emailConfigProperty.getEmailSendbox());
            mimeMessageHelper.setFrom(emailConfigProperty.getEmail());

            return mimeMessage;
        } catch (Exception e) {
            e.printStackTrace();

            throw new ErroInternoNoServidorException("Ocorreu um erro ao tentar enviar um email");
        }
    }

    private String prepararHtml(String corpo, Usuario usuario) throws Exception {
        Template template = configuration.getTemplate(corpo, LocaleContextHolder.getLocale());

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, usuario);
    }


}
