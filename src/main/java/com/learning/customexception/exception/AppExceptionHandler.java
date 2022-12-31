package com.learning.customexception.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {


    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<ApiExceptionResponse> handelRequestException(ApiException e, NativeWebRequest request) {
        String requestUri = extractUri(request);

        //get error key and get error value from message source

        return ResponseEntity
                .status(e.getStatusCode())
                .body(new ApiExceptionResponse()
                        .setPath(requestUri)
                        .setMessage(e.getMessage())
                        .setStatusCode(e.getStatusCode()));

    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiExceptionResponse> handelServerException(Exception e, NativeWebRequest request) {
        String requestUri = extractUri(request);
        log.error("Exception", e);
        // save to database

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new ApiExceptionResponse()
                        .setPath(requestUri)
                        .setMessage(e.getMessage())
                        .setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()));

    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<ApiExceptionResponse> handelAccessDeniedException(AccessDeniedException e, NativeWebRequest request) {
        String requestUri = extractUri(request);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiExceptionResponse()
                        .setPath(requestUri)
                        .setMessage(e.getMessage())
                        .setStatusCode(HttpStatus.FORBIDDEN.value()));

    }

    private String extractUri(NativeWebRequest request) {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        return servletRequest != null ? servletRequest.getRequestURI() : "";
    }

}
