package com.xor503.agenda.addcontact;

import com.xor503.agenda.addcontact.events.AddContactEvent;
import com.xor503.agenda.addcontact.ui.AddContactView;
import com.xor503.agenda.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by xor503 on 10/17/16.
 */

public class AddContactPresenterImpl implements AddContactPresenter{
    private EventBus eventBus;
    private AddContactView addContactView;
    private AddContactInteractor addContactInteractor;

    public AddContactPresenterImpl(EventBus eventBus, AddContactView addContactView, AddContactInteractor addContactInteractor) {
        this.eventBus = eventBus;
        this.addContactView = addContactView;
        this.addContactInteractor = addContactInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        addContactView = null;
    }

    @Override
    public void saveContact(List<String> data) {
        addContactInteractor.saveContact(data);
    }

    @Subscribe
    @Override
    public void onEventMainThread(AddContactEvent event) {
        if(event.getError() != null){
            addContactView.showError(event.getBeforeResource(), event.isBeforeResourceEnabled(),
                                     event.getCurrentResource(), event.isCurrentResourceEnabled(),
                                     event.getStringErrorResource(), event.getError());
        }
        if(event.getMessage() != null){
            addContactView.showMessage(event.getMessage());
            addContactView.cleanElements();
        }
    }

}
