package com.projects.logapi.api.exceptionhandler;

import com.projects.logapi.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error.Campo> campoList =
                ex.getBindingResult().getAllErrors().stream()
                        .map(err -> new Error.Campo(((FieldError) err).getField(), messageSource.getMessage(err, LocaleContextHolder.getLocale())))
                        .collect(Collectors.toList());

        Error error = new Error();
        error.setStatus(status.value());
        error.setTitulo("Um ou mais campos estão inválidos!");
        error.setLocalDateTime(LocalDateTime.now());
        error.setList(campoList);
        return this.handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Error error = new Error();
        error.setStatus(status.value());
        error.setTitulo(ex.getMessage());
        error.setLocalDateTime(LocalDateTime.now());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}