package com.dzenlab.nasajava.presentation.models;

import com.dzenlab.nasajava.presentation.constants.Code;

public class Message {

    private final String message;

    private final Code code;


    public Message(String message, Code code) {

        this.message = message;

        this.code = code;
    }

    public String getMessage() {

        return message;
    }

    public Code getCode() {

        return code;
    }
}
