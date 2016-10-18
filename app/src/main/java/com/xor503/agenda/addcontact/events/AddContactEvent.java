package com.xor503.agenda.addcontact.events;

import java.util.List;

/**
 * Created by xor503 on 10/17/16.
 */
public class AddContactEvent {
    private String error;
    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
