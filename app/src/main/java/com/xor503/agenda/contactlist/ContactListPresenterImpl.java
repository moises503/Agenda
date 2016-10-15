package com.xor503.agenda.contactlist;

import com.xor503.agenda.contactlist.events.ContactListEvent;
import com.xor503.agenda.contactlist.ui.ContactListView;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by xor503 on 10/13/16.
 */

public class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus eventBus;
    private ContactListView contactListView;
    private ContactListInteractor contactListInteractor;
    private StoredContactInteractor storedContactInteractor;

    public ContactListPresenterImpl(EventBus eventBus, ContactListView contactListView, ContactListInteractor contactListInteractor, StoredContactInteractor storedContactInteractor) {
        this.eventBus = eventBus;
        this.contactListView = contactListView;
        this.contactListInteractor = contactListInteractor;
        this.storedContactInteractor = storedContactInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        contactListView = null;
    }

    @Override
    public void getContacts() {
        contactListInteractor.execute();
    }

    @Override
    public void removeContact(Contact contact) {
        storedContactInteractor.executeDelete(contact);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        if(this.contactListView != null){
            switch (event.getType()){
                case ContactListEvent.READ_EVENT:
                    contactListView.setContacts(event.getContactList());
                    break;
                case ContactListEvent.UPDATE_EVENT:
                    contactListView.contactUpdated();
                    break;
                case ContactListEvent.DELETE_EVENT:
                    Contact contact = event.getContactList().get(0);
                    contactListView.contactDeleted(contact);
                    break;
            }
        }
    }
}
