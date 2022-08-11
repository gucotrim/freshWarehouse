package com.meli.freshWarehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ItsNotBelongException extends RuntimeException{

    public ItsNotBelongException(String message) {
        super(message);
    }

}
