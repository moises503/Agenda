package com.xor503.agenda.updatecontact.di;

import com.xor503.agenda.libs.base.EventBus;
import com.xor503.agenda.updatecontact.UpdateContactInteractor;
import com.xor503.agenda.updatecontact.UpdateContactInteractorImpl;
import com.xor503.agenda.updatecontact.UpdateContactPresenter;
import com.xor503.agenda.updatecontact.UpdateContactPresenterImpl;
import com.xor503.agenda.updatecontact.UpdateContactRepository;
import com.xor503.agenda.updatecontact.UpdateContactRepositoryImpl;
import com.xor503.agenda.updatecontact.ui.UpdateContactView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by x0r503 on 23/10/2016.
 */
@Module
public class UpdateContactModule {
    private UpdateContactView view;

    public UpdateContactModule(UpdateContactView view) {
        this.view = view;
    }

    @Provides @Singleton
    UpdateContactView providesUpdateContactView(){
        return this.view;
    }

    @Provides @Singleton
    UpdateContactPresenter providesUpdateContactPresenter(EventBus eventBus, UpdateContactView view, UpdateContactInteractor interactor){
        return new UpdateContactPresenterImpl(eventBus, view, interactor);
    }

    @Provides @Singleton
    UpdateContactInteractor providesUpdateContactInteractor(UpdateContactRepository repository){
        return new UpdateContactInteractorImpl(repository);
    }

    @Provides @Singleton
    UpdateContactRepository providesUpdateContactRepository(EventBus eventBus){
        return new UpdateContactRepositoryImpl(eventBus);
    }
}
