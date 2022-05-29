package com.joaoandrade.delivery.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProblemDetails {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String type;
    private final String title;
    private final Integer status;
    private final String detail;
    private final String userMessage;

    private final List<FieldMessage> errors = new ArrayList<>();

    public ProblemDetails(String type, String title, Integer status, String detail, String userMessage) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.userMessage = userMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void adicionarError(String field, String userMessage) {
        this.errors.add(new FieldMessage(field, userMessage));
    }
}

