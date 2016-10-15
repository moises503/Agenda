package com.xor503.agenda.contactlist;

/**
 * Created by xor503 on 10/13/16.
 */

public class ContactListInteractorImpl implements ContactListInteractor{
    private ContactListRepository contactListRepository;

    public ContactListInteractorImpl(ContactListRepository contactListRepository) {
        this.contactListRepository = contactListRepository;
    }

    @Override
    public void execute() {
        contactListRepository.getSavedContacts();
    }
}
