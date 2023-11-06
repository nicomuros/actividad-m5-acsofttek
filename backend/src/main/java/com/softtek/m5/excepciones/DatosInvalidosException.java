package com.softtek.m5.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST) // Cuando se lanza esta excepción, se envía un mensaje 404 de retorno
public class DatosInvalidosException extends RuntimeException{
    /**
     * Error que se lanza cuando se recibe una solicitud con datos no validos, o la ausencia de los mismos.
     * @param mensaje
     */
    public DatosInvalidosException(String mensaje){
        super(mensaje);
    }
}
