package com.foro_hub.foro_hub.infra.errores;

public class ValidacionException extends RuntimeException {
    
    public ValidacionException(String message) {
        super(message);
    }
}
