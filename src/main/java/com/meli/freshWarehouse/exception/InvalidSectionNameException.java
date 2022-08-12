package com.meli.freshWarehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

public class InvalidSectionNameException extends IllegalArgumentException {

    public InvalidSectionNameException(String message) {
        super(message);
    }

}