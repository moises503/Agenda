package com.xor503.agenda.updatecontact;

import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.updatecontact.events.UpdateContactEvent;

import java.util.List;

/**
 * Created by x0r503 on 23/10/2016.
 */

public interface UpdateContactPresenter {
    void onCreate();
    void onDestroy();
    void updateContact(List<String> data, Contact contact);
    void onEventMainThread(UpdateContactEvent event);
}
