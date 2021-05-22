package com.projects.logapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    private Integer status;
    private OffsetDateTime localDateTime;
    private String titulo;
    private List<Campo> list;

    @Getter
    @AllArgsConstructor
    public static class Campo {
        private String nome;
        private String message;
    }

}
