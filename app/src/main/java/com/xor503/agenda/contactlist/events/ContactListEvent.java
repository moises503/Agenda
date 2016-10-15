package com.xor503.agenda.contactlist.events;

import com.xor503.agenda.entities.Contact;

import java.util.List;

/**
 * Created by xor503 on 10/13/16.
 */

public class ContactListEvent {
    private int type;
    private List<Contact> contactList;

    public final static int READ_EVENT = 0;
    public final static int UPDATE_EVENT = 1;
    public final static int DELETE_EVENT = 2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
