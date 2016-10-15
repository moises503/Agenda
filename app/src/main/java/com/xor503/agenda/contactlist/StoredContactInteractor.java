package com.xor503.agenda.contactlist;

import com.xor503.agenda.entities.Contact;

/**
 * Created by xor503 on 10/13/16.
 */

public interface StoredContactInteractor {
    void executeUpdate(Contact contact);
    void executeDelete(Contact contact);
}
