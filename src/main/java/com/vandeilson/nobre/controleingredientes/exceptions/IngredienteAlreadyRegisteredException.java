package com.vandeilson.nobre.controleingredientes.exceptions;

import com.vandeilson.nobre.controleingredientes.entity.Ingredientes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredienteAlreadyRegisteredException extends Exception{

    public IngredienteAlreadyRegisteredException(Ingredientes ingrediente){
        super(String.format("Ingrediente with id '%1%s' and description '%2%s' is already registered", ingrediente.getId_ingrediente(), ingrediente.getDescricao()));
    }
}
