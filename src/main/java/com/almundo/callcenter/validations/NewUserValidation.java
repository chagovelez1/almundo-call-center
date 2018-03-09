package com.almundo.callcenter.validations;

import com.almundo.callcenter.utils.UserTypesEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewUserValidation {
    @NotNull
    private String type;
    @NotNull
    private String name;
    @NotNull
    private Long citicenship;
    @NotNull
    @JsonProperty("is_active")
    private boolean isActive;

    public void setType(String type) {
        this.type = UserTypesEnum.valueOf(type.toUpperCase()).getType();
    }
}
