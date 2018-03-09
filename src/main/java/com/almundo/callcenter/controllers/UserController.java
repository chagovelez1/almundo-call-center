package com.almundo.callcenter.controllers;

import com.almundo.callcenter.Router;
import com.almundo.callcenter.entities.User;
import com.almundo.callcenter.exceptions.ModelNotValidException;
import com.almundo.callcenter.services.UserService;
import com.almundo.callcenter.validations.UpdateUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.almundo.callcenter.validations.NewUserValidation;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = Router.USER, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity
    create(@RequestBody @Validated NewUserValidation request, BindingResult bindingResult) throws ModelNotValidException
    {
        if (bindingResult.hasErrors()) {
            throw new ModelNotValidException(bindingResult.getFieldErrors());
        }

        userService.create(request);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(Router.USER)
    public ResponseEntity
    update(@RequestBody @Validated UpdateUserValidation request, BindingResult bindingResult) throws ModelNotValidException {
        if (bindingResult.hasErrors()) {
            throw new ModelNotValidException(bindingResult.getFieldErrors());
        }

        userService.update(request);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(Router.USER)
    public ResponseEntity<List<User>>
    getAll(){
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }
}
