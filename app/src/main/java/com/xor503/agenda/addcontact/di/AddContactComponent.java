package com.xor503.agenda.addcontact.di;

import com.xor503.agenda.addcontact.AddContactPresenter;
import com.xor503.agenda.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by x0r503 on 23/10/2016.
 */
@Singleton
@Component(modules = {AddContactModule.class, LibsModule.class})
public interface AddContactComponent {
    AddContactPresenter getAddContactPresenter();
}
