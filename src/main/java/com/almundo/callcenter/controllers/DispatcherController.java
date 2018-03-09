package com.almundo.callcenter.controllers;

import com.almundo.callcenter.Router;
import com.almundo.callcenter.entities.Call;
import com.almundo.callcenter.exceptions.CustomError;
import com.almundo.callcenter.exceptions.ModelNotValidException;
import com.almundo.callcenter.parsers.DispatchCallResponseParser;
import com.almundo.callcenter.services.DispatcherService;
import com.almundo.callcenter.validations.DispatchCallValidation;
import com.almundo.callcenter.validations.EndCallValidation;
import com.almundo.callcenter.validations.NewUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DispatcherController {
    @Autowired
    private DispatcherService dispatcherService;

    @PostMapping(value = Router.DISPATCH_CALL)
    public ResponseEntity<DispatchCallResponseParser>
    dispatchCall(@RequestBody @Validated DispatchCallValidation request, BindingResult bindingResult) throws ModelNotValidException
    {
        if (bindingResult.hasErrors()) {
            throw new ModelNotValidException(bindingResult.getFieldErrors());
        }

        return new ResponseEntity(dispatcherService.dispatch(request), HttpStatus.OK);
    }

    @PostMapping(value = Router.END_CALL)
    public ResponseEntity<String>
    endCall(@RequestBody @Validated EndCallValidation request, BindingResult bindingResult) throws ModelNotValidException, CustomError {
        if (bindingResult.hasErrors()) {
            throw new ModelNotValidException(bindingResult.getFieldErrors());
        }
        dispatcherService.endCall(request.getCallId());

        return new ResponseEntity(HttpStatus.OK);
    }
}
