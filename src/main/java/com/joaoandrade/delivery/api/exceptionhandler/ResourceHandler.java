package com.joaoandrade.delivery.api.exceptionhandler;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.ErroInternoNoServidorException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.infrastructure.utility.ConstraintUniqueUtility;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class ResourceHandler extends ResponseEntityExceptionHandler {
    private static final String MENSAGEM_PADRAO_ERROR = "Ocorreu um erro inesperado, se o problema persistir recomendo que entre em contato com o desenvolvedor da API.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleErroGenerico(Exception ex, WebRequest request) {
        Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Ocorreu um erro interno no servidor. recomendo que entre em contato com o desenvolvedor da API.";
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, MENSAGEM_PADRAO_ERROR);

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        Error error = Error.ACESSO_NEGADO;
        HttpStatus status = HttpStatus.FORBIDDEN;
        String message = "Acesso negado. Você não tem permissão pra fazer está operação.";
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, message);

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex, WebRequest request) {
        Throwable cause = ex.getCause().getCause();

        if (cause instanceof FileSizeLimitExceededException) {
            return handleFileSizeLimitExceeded((FileSizeLimitExceededException) cause, request);
        }

        Error error = Error.TAMANHO_MAXIMO_UPLOAD_EXCEDIDO;
        HttpStatus status = HttpStatus.PAYLOAD_TOO_LARGE;
        String message = "Tamanho máximo de upload excedido";
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, message);

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleFileSizeLimitExceeded(FileSizeLimitExceededException ex, WebRequest request) {
        Error error = Error.TAMANHO_MAXIMO_UPLOAD_EXCEDIDO;
        HttpStatus status = HttpStatus.PAYLOAD_TOO_LARGE;
        Double tamanhoMaximoPermitido = ex.getPermittedSize() / 1048576.0;
        String message = String.format("O campo foto excede seu tamanho máximo permitido de %.1fMB.", tamanhoMaximoPermitido);
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, message);

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ErroInternoNoServidorException.class)
    public ResponseEntity<Object> handleErroInternoNoServidor(ErroInternoNoServidorException ex, WebRequest request) {
        Error error = Error.ERRO_INTERNO_NO_SERVIDOR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, MENSAGEM_PADRAO_ERROR);

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(SistemaException.class)
    public ResponseEntity<Object> handleSistema(SistemaException ex, WebRequest request) {
        Error error = Error.SISTEMA_EXCEPTION;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        Error error = Error.SISTEMA_EXCEPTION;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ConstraintUniqueUtility constraintUniqueUtility = ConstraintUniqueUtility.toConstraint(((ConstraintViolationException) ex.getCause()).getConstraintName());
        String message = constraintUniqueUtility == null ? MENSAGEM_PADRAO_ERROR : constraintUniqueUtility.getDescricao();
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, message);

        return handleExceptionInternal(ex, problemDetails, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable cause = ex.getCause();

        if (cause instanceof UnrecognizedPropertyException) {
            return handleUnrecognizedProperty((UnrecognizedPropertyException) cause, headers, status, request);
        }

        Error error = Error.ERROR_DESSERIALIZACAO_JSON;
        String message = "Ocorreu um erro na desserialização do JSON. verifique se os dados foram inseridos corretamente no corpo da requisição.";
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, MENSAGEM_PADRAO_ERROR);

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    private ResponseEntity<Object> handleUnrecognizedProperty(UnrecognizedPropertyException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = Error.PROPRIEDADE_INEXISTENTE;
        String message = String.format("A propriedade '%s' é inexistente", ex.getPropertyName());
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, MENSAGEM_PADRAO_ERROR);

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = Error.METODO_SOLICITACAO_NAO_SUPORTADO;
        String message = String.format("O metodo de solicitação '%s' não é suportado. Os metodos suportados são: %s", ex.getMethod(), Arrays.toString(ex.getSupportedMethods()));
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, MENSAGEM_PADRAO_ERROR);

        return handleExceptionInternal(ex, problemDetails, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = Error.ENDPOINT_INEXISTENTE;
        String message = String.format("O endpoint [%s%s] é inexistente", ex.getHttpMethod(), ex.getRequestURL());
        ProblemDetails problemDetails = new ProblemDetails(error.getType(), error.getTitle(), status.value(), message, MENSAGEM_PADRAO_ERROR);

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
