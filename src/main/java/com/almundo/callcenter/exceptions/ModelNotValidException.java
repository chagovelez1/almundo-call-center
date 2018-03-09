package com.almundo.callcenter.exceptions;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ModelNotValidException extends Throwable
{

    private ArrayList<String> descriptions = new ArrayList<>();

    public ModelNotValidException(List<FieldError> fieldErrors)
    {
        for (FieldError error : fieldErrors) {
            descriptions.add( error.getField() + " " + error.getDefaultMessage());
        }
    }

    public String[] getOwnMessages()
    {
        String[] res = new String[descriptions.size()];
        for (int i = 0; i < descriptions.size(); i++) {
            res[i] = descriptions.get(i);
        }
        return res;
    }
}
