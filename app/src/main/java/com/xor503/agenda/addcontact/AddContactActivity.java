package com.xor503.agenda.addcontact;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.xor503.agenda.R;
import com.xor503.agenda.entities.Contact;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddContactActivity extends AppCompatActivity {

    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.txtLastName)
    EditText txtLastName;
    @BindView(R.id.txtPhone)
    EditText txtPhone;
    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.txtFb)
    EditText txtFb;
    @BindView(R.id.txtTweet)
    EditText txtTweet;
    @BindView(R.id.btnAddContact)
    Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAddContact)
    public void onClick() {
        Contact contact = new Contact();
        contact.setName(txtName.getText().toString());
        contact.setLastName(txtLastName.getText().toString());
        contact.setPhone(txtPhone.getText().toString());
        contact.setEmail(txtEmail.getText().toString());
        contact.setFb(txtFb.getText().toString());
        contact.setTweet(txtTweet.getText().toString());
        contact.save();
    }
}
