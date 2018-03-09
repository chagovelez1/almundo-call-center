package com.almundo.callcenter.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "singleton")
public class UserTaken {
    private List<String> userIds = new ArrayList<>();

    public boolean validateUserIsNotInDispatchProcess(String userId){
        if (userIds.contains(userId)){
            return false;
        }

        return userIds.add(userId);
    }

    public boolean finishDispatchProcess(String id) {
        return userIds.remove(id);
    }
}
