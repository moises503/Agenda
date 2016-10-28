package com.xor503.agenda.addcontact;

import com.xor503.agenda.R;
import com.xor503.agenda.addcontact.events.AddContactEvent;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.libs.Validation;
import com.xor503.agenda.libs.base.EventBus;

import java.util.List;

/**
 * Created by xor503 on 10/17/16.
 */

public class AddContactRepositoryImpl implements AddContactRepository{
    private EventBus eventBus;

    public AddContactRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void insertContact(List<String> data) {
        if(Validation.isEmpty(data.get(0))){
            post(0, false, R.id.layoutName, true, R.string.addcontact_name_error, " es requerido");
        }else if(Validation.isEmpty(data.get(1))){
            post(R.id.layoutName, false, R.id.layoutLastName, true, R.string.addcontact_lastname_error," son requeridos");
        } else if(Validation.isEmpty(data.get(2))){
            post(R.id.layoutLastName, false, R.id.layoutPhone, true, R.string.addcontact_phone_error," es requerido");
        } else if(Validation.isEmpty(data.get(3))){
            post(R.id.layoutPhone, false, R.id.layoutEmail, true, R.string.addcontact_email_error, " es requerido");
        } else if(Validation.isEmpty(data.get(4))){
            post(R.id.layoutEmail, false, R.id.layoutFb, true, R.string.addcontact_facebook_error," es requerido");
        } else if(Validation.isEmpty(data.get(5))){
            post(R.id.layoutFb, false, R.id.layoutTweet, true, R.string.addcontact_twitter_error," es requerido");
        } else {
            Contact contact = new Contact();
            contact.setName(data.get(0));
            contact.setLastName(data.get(1));
            contact.setPhone(data.get(2));
            contact.setEmail(data.get(3));
            contact.setFb(data.get(4));
            contact.setTweet(data.get(5));
            contact.save();
            post(R.id.layoutTweet, false, 0, false, 0, "");
            post("Contacto agregado correctamente");
        }
    }

    private void post(String message){
        post(0, false, 0,false, 0,null, message);
    }

    private void post(int beforeResource, boolean beforeResourceEnabled, int currentResource, boolean currentResourceEnabled, int errorResource, String error){
        post(beforeResource, beforeResourceEnabled, currentResource, currentResourceEnabled, errorResource, error, null);
    }

    private void post(int beforeResource, boolean beforeResourceEnabled, int currentResource, boolean currentResourceEnabled,  int errorResource, String error, String message){
        AddContactEvent event = new AddContactEvent();
        event.setError(error);
        event.setMessage(message);
        event.setCurrentResource(currentResource);
        event.setCurrentResourceEnabled(currentResourceEnabled);
        event.setBeforeResource(beforeResource);
        event.setBeforeResourceEnabled(beforeResourceEnabled);
        event.setStringErrorResource(errorResource);
        eventBus.post(event);
    }


}
