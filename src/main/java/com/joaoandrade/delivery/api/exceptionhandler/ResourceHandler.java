package com.joaoandrade.delivery.api.exceptionhandler;

import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {
    private static final String MENSAGEM_PADRAO_ERROR = "Ocorreu um erro inesperado, se o problema persistir recomendo que entre em contato com o desenvolvedor da API.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(SistemaException.class)
    public ResponseEntity<Object> handleSistema(SistemaException ex, WebRequest request) {
        Error error = Error.NEGOCIO_EXCEPTION;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), ex.getMessage(), ex.getMessage());

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {
        Error error = Error.ENTIDADE_NAO_ENCONTRADA_EXCEPTION;
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), ex.getMessage(), ex.getMessage());

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = Error.ERROR_VALIDACAO_DADOS;
        String message = "Ocorreu um erro de validação dos dados";
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, message);

        for (ObjectError objectError : ex.getAllErrors()) {
            String field = objectError.getObjectName();

            if (objectError instanceof FieldError) {
                field = ((FieldError) objectError).getField();
            }

            String userMessage = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

            problemDetails.adicionarError(field, userMessage);
        }

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new ProblemDetails(null, null, status.value(), status.getReasonPhrase(), MENSAGEM_PADRAO_ERROR);
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
