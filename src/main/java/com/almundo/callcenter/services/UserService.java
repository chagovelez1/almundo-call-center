package com.almundo.callcenter.services;

import com.almundo.callcenter.entities.User;
import com.almundo.callcenter.repositories.UserRepository;
import com.almundo.callcenter.validations.NewUserValidation;
import com.almundo.callcenter.validations.UpdateUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void create(NewUserValidation newUserValidation) {
        User user = new User();
        user.setCiticenship(newUserValidation.getCiticenship());
        user.setType(newUserValidation.getType());
        user.setName(newUserValidation.getName());
        user.setActive(newUserValidation.isActive());

        userRepository.insert(user);
    }

    public void update(UpdateUserValidation updateUserValidation){
        User user = userRepository.findById(updateUserValidation.getId());
        if (updateUserValidation.getCiticenship() != null) user.setCiticenship(updateUserValidation.getCiticenship());
        if (updateUserValidation.getType() != null) user.setType(updateUserValidation.getType());
        if (updateUserValidation.getIsActive() != null) user.setActive(updateUserValidation.getIsActive());

        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
