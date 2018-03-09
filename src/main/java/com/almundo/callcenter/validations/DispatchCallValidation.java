package com.almundo.callcenter.validations;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DispatchCallValidation {
    @NotNull
    private String telephone;
}
