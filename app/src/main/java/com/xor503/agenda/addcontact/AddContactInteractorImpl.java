package com.xor503.agenda.addcontact;

import java.util.List;

/**
 * Created by xor503 on 10/17/16.
 */

public class AddContactInteractorImpl implements AddContactInteractor{
    private AddContactRepository addContactRepository;

    public AddContactInteractorImpl(AddContactRepository addContactRepository) {
        this.addContactRepository = addContactRepository;
    }

    @Override
    public void saveContact(List<String> data) {
        addContactRepository.insertContact(data);
    }
}
