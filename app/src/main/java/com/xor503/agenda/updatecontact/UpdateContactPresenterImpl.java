package com.xor503.agenda.updatecontact;

import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.libs.base.EventBus;
import com.xor503.agenda.updatecontact.events.UpdateContactEvent;
import com.xor503.agenda.updatecontact.ui.UpdateContactView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by x0r503 on 23/10/2016.
 */

public class UpdateContactPresenterImpl implements UpdateContactPresenter{
    private EventBus eventBus;
    private UpdateContactView view;
    private UpdateContactInteractor interactor;

    public UpdateContactPresenterImpl(EventBus eventBus, UpdateContactView view, UpdateContactInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void updateContact(List<String> data, Contact contact) {
        interactor.updateContact(data, contact);
    }

    @Subscribe
    @Override
    public void onEventMainThread(UpdateContactEvent event) {
        if(event.getError() != null){
            view.showError(event.getBeforeResource(), event.isBeforeResourceEnabled(),
                    event.getCurrentResource(), event.isCurrentResourceEnabled(),
                    event.getStringErrorResource(), event.getError());
        }
        if(event.getMessage() != null){
            view.showMessage(event.getMessage(), event.getContact());
            view.cleanElements();
        }
    }
}
