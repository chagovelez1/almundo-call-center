package com.almundo.callcenter.exceptions;

public class BadResponse
{

    private String message;

    private Exception exception;

    public BadResponse(String message, Exception exception)
    {
        this.message = message;
        this.exception = exception;
    }

}
