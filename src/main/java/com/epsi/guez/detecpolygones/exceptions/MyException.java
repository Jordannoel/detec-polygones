package com.epsi.guez.detecpolygones.exceptions;

import java.util.HashMap;
import java.util.Map;

public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;

    private Map<String, String> messages = new HashMap<>();

    public Map<String, String> getMessages() {
        return messages;
    }

    public void addMessage(String code, String message) {
        messages.put(code, message);
    }

    public boolean mustBeThrown() {
        return !messages.isEmpty();
    }
}