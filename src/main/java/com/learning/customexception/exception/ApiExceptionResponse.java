package com.learning.customexception.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

@Accessors(chain = true)
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiExceptionResponse {

    private String path;
    private String message;
    private int statusCode;
    private String errorKey;
    private Map<String, String> userMessage;
    private int exceptionTypeCode;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
