package com.xor503.agenda.addcontact.di;

import com.xor503.agenda.addcontact.AddContactInteractor;
import com.xor503.agenda.addcontact.AddContactInteractorImpl;
import com.xor503.agenda.addcontact.AddContactPresenter;
import com.xor503.agenda.addcontact.AddContactPresenterImpl;
import com.xor503.agenda.addcontact.AddContactRepository;
import com.xor503.agenda.addcontact.AddContactRepositoryImpl;
import com.xor503.agenda.addcontact.ui.AddContactView;
import com.xor503.agenda.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by x0r503 on 23/10/2016.
 */
@Module
public class AddContactModule {
    private AddContactView view;

    public AddContactModule(AddContactView view) {
        this.view = view;
    }

    @Singleton @Provides
    AddContactView providesAddContactView(){
        return this.view;
    }

    @Singleton @Provides
    AddContactPresenter providesAddContactPresenter(EventBus eventBus, AddContactView addContactView, AddContactInteractor addContactInteractor){
        return new AddContactPresenterImpl(eventBus, addContactView, addContactInteractor);
    }

    @Singleton @Provides
    AddContactInteractor providesAddContactInteractorImpl(AddContactRepository addContactRepository){
        return new AddContactInteractorImpl(addContactRepository);
    }

    @Singleton @Provides
    AddContactRepository providesAddContactRepositoryImpl(EventBus eventBus){
        return new AddContactRepositoryImpl(eventBus);
    }
}
