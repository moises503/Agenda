package com.xor503.agenda.updatecontact;

import com.xor503.agenda.entities.Contact;

import java.util.List;

/**
 * Created by x0r503 on 23/10/2016.
 */

public class UpdateContactInteractorImpl implements UpdateContactInteractor{
    private UpdateContactRepository repository;

    public UpdateContactInteractorImpl(UpdateContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public void updateContact(List<String> data, Contact contact) {
        repository.updateContact(data, contact);
    }
}
