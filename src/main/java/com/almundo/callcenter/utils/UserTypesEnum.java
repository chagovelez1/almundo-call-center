package com.almundo.callcenter.utils;

import lombok.Getter;

@Getter
public enum UserTypesEnum {

    OPERADOR("operador"),
    SUPERVISOR("supervisor"),
    DIRECTOR("director");

    private String type;

    UserTypesEnum(String type) {
        this.type = type;
    }
}
