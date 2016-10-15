package com.xor503.agenda.contactlist;

import com.xor503.agenda.contactlist.events.ContactListEvent;
import com.xor503.agenda.entities.Contact;

/**
 * Created by xor503 on 10/13/16.
 */

public interface ContactListPresenter {
    void onCreate();
    void onDestroy();

    void getContacts();
    void removeContact(Contact contact);
    void onEventMainThread(ContactListEvent event);

}
