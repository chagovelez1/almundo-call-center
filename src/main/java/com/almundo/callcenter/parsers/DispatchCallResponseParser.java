package com.almundo.callcenter.parsers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispatchCallResponseParser {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("call_id")
    private String callId;
}
