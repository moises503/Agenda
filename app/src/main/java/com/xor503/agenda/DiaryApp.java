package com.xor503.agenda;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.xor503.agenda.addcontact.di.AddContactComponent;
import com.xor503.agenda.addcontact.di.AddContactModule;
import com.xor503.agenda.addcontact.di.DaggerAddContactComponent;
import com.xor503.agenda.addcontact.ui.AddContactActivity;
import com.xor503.agenda.addcontact.ui.AddContactView;
import com.xor503.agenda.contactlist.di.ContactListComponent;
import com.xor503.agenda.contactlist.di.ContactListModule;
import com.xor503.agenda.contactlist.di.DaggerContactListComponent;
import com.xor503.agenda.contactlist.ui.ContactListActivity;
import com.xor503.agenda.contactlist.ui.ContactListView;
import com.xor503.agenda.contactlist.ui.adapters.OnItemClickListener;
import com.xor503.agenda.libs.di.LibsModule;

/**
 * Created by xor503 on 10/13/16.
 */

public class DiaryApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public ContactListComponent getContactListComponent(ContactListActivity activity, ContactListView view, OnItemClickListener listener){
        return DaggerContactListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .contactListModule(new ContactListModule(view, listener, activity))
                .build();
    }

    public AddContactComponent getAddContactComponent(AddContactActivity activity, AddContactView view){
        return DaggerAddContactComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .addContactModule(new AddContactModule(view))
                .build();
    }

}
