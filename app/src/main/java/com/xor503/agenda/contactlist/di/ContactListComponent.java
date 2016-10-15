package com.xor503.agenda.contactlist.di;

import com.xor503.agenda.contactlist.ContactListPresenter;
import com.xor503.agenda.contactlist.ui.adapters.ContactListAdapter;
import com.xor503.agenda.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xor503 on 10/14/16.
 */
@Singleton
@Component(modules = {ContactListModule.class, LibsModule.class})
public interface ContactListComponent {
    ContactListAdapter getAdapter();
    ContactListPresenter getContactListPresenter();
}
