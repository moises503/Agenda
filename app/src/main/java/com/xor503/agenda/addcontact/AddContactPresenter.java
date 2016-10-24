package com.xor503.agenda.addcontact;

import com.xor503.agenda.addcontact.events.AddContactEvent;

import java.util.List;

/**
 * Created by xor503 on 10/17/16.
 */

public interface AddContactPresenter {
    void onCreate();
    void onDestroy();
    void saveContact(List<String> data);
    void onEventMainThread(AddContactEvent event);

}
