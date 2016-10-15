package com.xor503.agenda.contactlist;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.xor503.agenda.contactlist.events.ContactListEvent;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.libs.base.EventBus;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xor503 on 10/13/16.
 */

public class ContactListRepositoryImpl implements ContactListRepository {
    private EventBus eventBus;

    public ContactListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedContacts() {
        List<Contact> stored = new Select().from(Contact.class).queryList();
        post(ContactListEvent.READ_EVENT, stored);
    }

    @Override
    public void updateContact(Contact contact) {
        contact.update();
        post();
    }

    @Override
    public void removeContact(Contact contact) {
        contact.delete();
        post(ContactListEvent.DELETE_EVENT, Arrays.asList(contact));
    }

    private void post(int type, List<Contact> stored) {
        ContactListEvent event = new ContactListEvent();
        event.setType(type);
        event.setContactList(stored);
        eventBus.post(event);
    }

    private void post(){
        post(ContactListEvent.UPDATE_EVENT, null);
    }
}
