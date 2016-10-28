package com.xor503.agenda.updatecontact;

import com.xor503.agenda.entities.Contact;

import java.util.List;

/**
 * Created by x0r503 on 23/10/2016.
 */

public interface UpdateContactRepository {
    void updateContact(List<String> data, Contact contact);
}
