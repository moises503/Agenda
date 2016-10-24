package com.xor503.agenda.contactlist.di;

import com.xor503.agenda.contactlist.ContactListInteractor;
import com.xor503.agenda.contactlist.ContactListInteractorImpl;
import com.xor503.agenda.contactlist.ContactListPresenter;
import com.xor503.agenda.contactlist.ContactListPresenterImpl;
import com.xor503.agenda.contactlist.ContactListRepository;
import com.xor503.agenda.contactlist.ContactListRepositoryImpl;
import com.xor503.agenda.contactlist.StoredContactInteractor;
import com.xor503.agenda.contactlist.StoredContactInteractorImpl;
import com.xor503.agenda.contactlist.ui.ContactListActivity;
import com.xor503.agenda.contactlist.ui.ContactListView;
import com.xor503.agenda.contactlist.ui.adapters.ContactListAdapter;
import com.xor503.agenda.contactlist.ui.adapters.OnItemClickListener;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.libs.base.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xor503 on 10/14/16.
 */
@Module
public class ContactListModule {
    ContactListView view;
    OnItemClickListener clickListener;
    ContactListActivity activity;

    public ContactListModule(ContactListView view, OnItemClickListener clickListener, ContactListActivity activity) {
        this.view = view;
        this.clickListener = clickListener;
        this.activity = activity;
    }

    @Provides @Singleton
    ContactListView providesContactListView(){
        return this.view;
    }

    @Provides @Singleton
    ContactListPresenter providesContactListPresenter(EventBus eventBus, ContactListView contactListView, ContactListInteractor contactListInteractor, StoredContactInteractor storedContactInteractor){
        return new ContactListPresenterImpl(eventBus, contactListView, contactListInteractor, storedContactInteractor);
    }

    @Provides @Singleton
    StoredContactInteractor providesStoredContactInteractor(ContactListRepository contactListRepository) {
        return new StoredContactInteractorImpl(contactListRepository);
    }

    @Provides @Singleton
    ContactListInteractor providesContactListInteractor(ContactListRepository contactListRepository){
        return new ContactListInteractorImpl(contactListRepository);
    }

    @Provides @Singleton
    ContactListRepository providesContactListRepository(EventBus eventBus){
        return new ContactListRepositoryImpl(eventBus);
    }

    @Provides @Singleton
    ContactListAdapter providesContactListAdapter(ContactListActivity activity, List<Contact> contactList, OnItemClickListener onItemClickListener){
        return new ContactListAdapter(contactList, onItemClickListener, activity);
    }
    @Provides @Singleton
    ContactListActivity providesContactListActivity(){
        return this.activity;
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides @Singleton
    List<Contact> providesEmptyList(){
        return new ArrayList<Contact>();
    }
}
