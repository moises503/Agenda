package com.xor503.agenda.contactlist;

import com.xor503.agenda.entities.Contact;

/**
 * Created by xor503 on 10/13/16.
 */

public class StoredContactInteractorImpl implements StoredContactInteractor{
    private ContactListRepository contactListRepository;

    public StoredContactInteractorImpl(ContactListRepository contactListRepository) {
        this.contactListRepository = contactListRepository;
    }

    @Override
    public void executeUpdate(Contact contact) {
        contactListRepository.updateContact(contact);
    }

    @Override
    public void executeDelete(Contact contact) {
        contactListRepository.removeContact(contact);
    }
}
