package com.projects.logapi.domain.exception;

public class NegocioException extends RuntimeException{
    public NegocioException(String msg) {
        super(msg);
    }
}
