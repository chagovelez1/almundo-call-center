package com.almundo.callcenter.validations;

import com.almundo.callcenter.utils.UserTypesEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserValidation {

    @NotNull
    private String id;

    private String type;
    private String name;
    private Long citicenship;
    @JsonProperty("is_active")
    private Boolean isActive;

    public void setType(String type) {
        this.type = type == null ? null : UserTypesEnum.valueOf(type.toUpperCase()).getType();
    }
}
