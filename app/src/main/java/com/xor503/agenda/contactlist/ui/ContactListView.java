package com.xor503.agenda.contactlist.ui;

import com.xor503.agenda.entities.Contact;

import java.util.List;

/**
 * Created by xor503 on 10/13/16.
 */

public interface ContactListView {
    void setContacts(List<Contact> data);
    void contactUpdated();
    void contactDeleted(Contact contact);

}
