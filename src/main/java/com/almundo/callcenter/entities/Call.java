package com.almundo.callcenter.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "calls")
public class Call {
    @Id
    private String id;

    private String telephoneNumber;
    private LocalDateTime incomeTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @DBRef
    private User assignedUser;
}
