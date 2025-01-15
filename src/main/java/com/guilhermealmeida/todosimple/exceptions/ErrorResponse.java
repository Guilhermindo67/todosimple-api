package com.guilhermealmeida.todosimple.exceptions;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> errors;   

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class ValidationError{
        private final String field;
        private final String message;
    }

    

}