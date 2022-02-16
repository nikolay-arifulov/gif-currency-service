package com.kooliz.gifcurrencyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CurrencyNotFoundException extends RuntimeException {

    public CurrencyNotFoundException(Throwable cause) {
        super(cause);
    }
}
