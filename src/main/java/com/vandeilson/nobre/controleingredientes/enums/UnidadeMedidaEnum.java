package com.vandeilson.nobre.controleingredientes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnidadeMedidaEnum {
    G("g"),
    KG("kg"),
    L("l"),
    ML("ml");

    private final String description;
}
