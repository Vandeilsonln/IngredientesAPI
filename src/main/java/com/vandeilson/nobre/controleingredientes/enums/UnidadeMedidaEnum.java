package com.vandeilson.nobre.controleingredientes.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UnidadeMedidaEnum {
    g("g"),
    kg("kg"),
    l("l"),
    ml("ml");

    private final String description;
}
