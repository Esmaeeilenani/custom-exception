package com.learning.customexception.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Accessors(chain = true)
public class ApiException extends RuntimeException {
    private String message;


    private int statusCode = HttpStatus.BAD_REQUEST.value();


    public ApiException(String message) {
        this.message = message;

    }


}

