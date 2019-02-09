package com.examen.ingreso.meli.sistema.solar.resources.ExceptionRest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Not exist Weather  info for day")
public class DayNotFoundException extends Exception {

    private static final long serialVersionUID = 100L;
}
