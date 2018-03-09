package com.almundo.callcenter.entities;

import com.almundo.callcenter.utils.UserTypesEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private long citicenship;

    private String type;
    private String name;
    @JsonProperty("is_active")
    private boolean isActive;

    @DBRef
    private Call currentCall;
    private LocalDateTime currentCallStartTime;

    public void setType(String type) {
        this.type = UserTypesEnum.valueOf(type.toUpperCase()).getType();
    }
}
