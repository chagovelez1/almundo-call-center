package com.almundo.callcenter.exceptions;

import java.util.ArrayList;

public class CustomError extends Throwable
{

    private ArrayList<String> descriptions = new ArrayList<>();

    public CustomError(String message)
    {
        descriptions.add(message);
    }

    public CustomError(ArrayList<String> messages)
    {
        descriptions.addAll(messages);
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
