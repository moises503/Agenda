package com.xor503.agenda.updatecontact.ui;

import com.xor503.agenda.entities.Contact;

/**
 * Created by x0r503 on 23/10/2016.
 */

public interface UpdateContactView {
    void showError(int beforeResource, boolean beforeResourceEnabled, int currentResource, boolean currentResourceEnabled, int errorResource, String error);
    void cleanElements();
    void showMessage(String message, Contact contact);
}
