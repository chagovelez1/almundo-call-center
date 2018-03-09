package com.almundo.callcenter.fakeFactory;

import com.almundo.callcenter.entities.User;
import com.almundo.callcenter.utils.UserTypesEnum;
import lombok.Getter;

import java.util.Random;

@Getter
public class UserFactory {
    private User user = new User();
    private Random random = new Random();

    public UserFactory(){
        user.setActive(random.nextBoolean());
        user.setCiticenship(random.nextLong());
        user.setType(UserTypesEnum.OPERADOR.getType());
        user.setName("random user");
    }
}
