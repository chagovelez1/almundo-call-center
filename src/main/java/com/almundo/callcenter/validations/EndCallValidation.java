package com.almundo.callcenter.validations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EndCallValidation {
    @JsonProperty("call_id")
    @NotNull
    private String callId;
}
