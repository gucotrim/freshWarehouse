package com.meli.freshWarehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class RepresentativeNotFoundException extends RuntimeException{

    public RepresentativeNotFoundException(String message) {
        super(message);
    }

}
