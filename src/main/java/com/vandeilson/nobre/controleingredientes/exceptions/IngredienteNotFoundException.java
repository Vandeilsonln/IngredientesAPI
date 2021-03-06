package com.vandeilson.nobre.controleingredientes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IngredienteNotFoundException extends Exception{

    public IngredienteNotFoundException(Long id){
        super(String.format("Ingrediente with id '%s' not found", id));
    }
}
