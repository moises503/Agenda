package com.xor503.agenda.addcontact.events;

import java.util.List;

/**
 * Created by xor503 on 10/17/16.
 */
public class AddContactEvent {
    private String error;
    private String message;
    private int currentResource;
    private boolean currentResourceEnabled;
    private int beforeResource;
    private boolean beforeResourceEnabled;
    private int stringErrorResource;

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

    public int getCurrentResource() {
        return currentResource;
    }

    public void setCurrentResource(int currentResource) {
        this.currentResource = currentResource;
    }

    public boolean isCurrentResourceEnabled() {
        return currentResourceEnabled;
    }

    public void setCurrentResourceEnabled(boolean currentResourceEnabled) {
        this.currentResourceEnabled = currentResourceEnabled;
    }

    public int getBeforeResource() {
        return beforeResource;
    }

    public void setBeforeResource(int beforeResource) {
        this.beforeResource = beforeResource;
    }

    public boolean isBeforeResourceEnabled() {
        return beforeResourceEnabled;
    }

    public void setBeforeResourceEnabled(boolean beforeResourceEnabled) {
        this.beforeResourceEnabled = beforeResourceEnabled;
    }

    public int getStringErrorResource() {
        return stringErrorResource;
    }

    public void setStringErrorResource(int stringErrorResource) {
        this.stringErrorResource = stringErrorResource;
    }
}
