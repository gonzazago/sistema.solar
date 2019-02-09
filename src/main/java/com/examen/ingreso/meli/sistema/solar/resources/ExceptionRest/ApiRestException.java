package com.examen.ingreso.meli.sistema.solar.resources.ExceptionRest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiRestException {

    private HttpStatus status;

    @JsonInclude(Include.NON_EMPTY)
    private String message;

    @JsonInclude(Include.NON_EMPTY)
    private List<String> errors;

    private ApiRestException(final HttpStatus status, final String message, final List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public static ApiRestException create(final HttpStatus status) {
        return create(status, null);
    }

    public static ApiRestException create(final HttpStatus status, final String message) {
        return createWithErrors(status, message, Collections.emptyList());
    }

    public static ApiRestException createWithErrors(
            final HttpStatus status, final String message, final String error) {
        return createWithErrors(status, message, Collections.singletonList(error));
    }

    public static ApiRestException createWithErrors(
            final HttpStatus status, final String message, final List<String> errors) {
        return new ApiRestException(status, message, errors);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
