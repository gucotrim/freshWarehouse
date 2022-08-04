package com.meli.freshWarehouse.handler;

import com.meli.freshWarehouse.exception.ExceededStock;
import com.meli.freshWarehouse.exception.ExceptionDetails;
import com.meli.freshWarehouse.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

@ControllerAdvice
public class NotFoundExeHandler {


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(NotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Data object not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(FileNotFoundException ex) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Data object not found.")
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request.")
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Bad Request. Param " + e.getName() +
                        " has invalid value: " + e.getValue() + ". Expected: Boolean"
                )
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request.")
                .status(HttpStatus.BAD_REQUEST.value())
                .message("JSON parse error: Cannot deserialize value of type")
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(HttpServerErrorException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal server Error")
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(ExceededStock.class)
    public ResponseEntity<ExceptionDetails> handlerNotFoundEx(ExceededStock e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .title("Bad Request.")
                .status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .message(e.getMessage())
                .localDateTime(LocalDateTime.now())
                .build(),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}
