package com.xor503.agenda.addcontact.ui;

import com.xor503.agenda.entities.Contact;

/**
 * Created by xor503 on 10/17/16.
 */

public interface AddContactView {
    void showError(int beforeResource, boolean beforeResourceEnabled, int currentResource, boolean currentResourceEnabled, int errorResource, String error);
    void cleanElements();
    void showMessage(String message);
}
