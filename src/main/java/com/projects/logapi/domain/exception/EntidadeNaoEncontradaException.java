package com.projects.logapi.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
