package com.xor503.agenda.updatecontact.di;


import com.xor503.agenda.libs.di.LibsModule;
import com.xor503.agenda.updatecontact.UpdateContactPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by x0r503 on 23/10/2016.
 */
@Singleton
@Component(modules = {UpdateContactModule.class, LibsModule.class})
public interface UpdateContactComponent {
    UpdateContactPresenter getUpdateContactPresenter();
}
