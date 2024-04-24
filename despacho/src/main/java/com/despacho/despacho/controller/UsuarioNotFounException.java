package com.despacho.despacho.controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFounException extends RuntimeException{

    public UsuarioNotFounException(String message) {
        super(message);
    }

    
}
