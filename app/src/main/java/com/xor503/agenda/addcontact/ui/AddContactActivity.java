package com.xor503.agenda.addcontact.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xor503.agenda.DiaryApp;
import com.xor503.agenda.R;
import com.xor503.agenda.addcontact.AddContactPresenter;
import com.xor503.agenda.addcontact.di.AddContactComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddContactActivity extends AppCompatActivity implements AddContactView {


    @BindView(R.id.txtName)
    TextInputEditText txtName;
    @BindView(R.id.layoutName)
    TextInputLayout layoutName;
    @BindView(R.id.txtLastName)
    TextInputEditText txtLastName;
    @BindView(R.id.layoutLastName)
    TextInputLayout layoutLastName;
    @BindView(R.id.txtPhone)
    TextInputEditText txtPhone;
    @BindView(R.id.layoutPhone)
    TextInputLayout layoutPhone;
    @BindView(R.id.txtEmail)
    TextInputEditText txtEmail;
    @BindView(R.id.layoutEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.txtFb)
    TextInputEditText txtFb;
    @BindView(R.id.layoutFb)
    TextInputLayout layoutFb;
    @BindView(R.id.txtTweet)
    TextInputEditText txtTweet;
    @BindView(R.id.layoutTweet)
    TextInputLayout layoutTweet;
    @BindView(R.id.btnAddContact)
    Button btnAddContact;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    private AddContactPresenter presenter;
    private AddContactComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        presenter.onCreate();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.btnAddContact)
    public void onClick() {
        /*Contact contact = new Contact();
        contact.setName(txtName.getText().toString());
        contact.setLastName(txtLastName.getText().toString());
        contact.setPhone(txtPhone.getText().toString());
        contact.setEmail(txtEmail.getText().toString());
        contact.setFb(txtFb.getText().toString());
        contact.setTweet(txtTweet.getText().toString());
        contact.save();*/

        List<String> data = new ArrayList<String>();
        data.add(txtName.getText().toString());
        data.add(txtLastName.getText().toString());
        data.add(txtPhone.getText().toString());
        data.add(txtEmail.getText().toString());
        data.add(txtFb.getText().toString());
        data.add(txtTweet.getText().toString());
        presenter.saveContact(data);
    }

    @Override
    public void showError(int beforeResource, boolean beforeResourceEnabled, int currentResource, boolean currentResourceEnabled, int errorResource, String error) {
        if (beforeResource == 0) {
            showCurrent(currentResource, currentResourceEnabled, errorResource, error);
        } else {
            if (currentResource == 0) {
                showBefore(beforeResource, beforeResourceEnabled);
            } else {
                showBeforeAndCurrent(beforeResource, beforeResourceEnabled, currentResource, currentResourceEnabled, errorResource, error);
            }
        }
    }

    private void showCurrent(int currentResource, boolean currentResourceEnabled, int errorResource, String error) {
        TextInputLayout current;
        current = (TextInputLayout) findViewById(currentResource);
        current.setError(getString(errorResource, error));
        current.setErrorEnabled(currentResourceEnabled);
    }

    private void showBefore(int beforeResource, boolean beforeResourceEnabled) {
        TextInputLayout before;
        before = (TextInputLayout) findViewById(beforeResource);
        before.setErrorEnabled(beforeResourceEnabled);
    }

    private void showBeforeAndCurrent(int beforeResource, boolean beforeResourceEnabled, int currentResource, boolean currentResourceEnabled, int errorResource, String error) {
        TextInputLayout before;
        TextInputLayout current;
        before = (TextInputLayout) findViewById(beforeResource);
        before.setErrorEnabled(beforeResourceEnabled);
        current = (TextInputLayout) findViewById(currentResource);
        current.setError(getString(errorResource, error));
        current.setErrorEnabled(currentResourceEnabled);
    }

    @Override
    public void cleanElements() {
        txtName.setText("");
        txtLastName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtFb.setText("");
        txtTweet.setText("");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void setupInjection() {
        DiaryApp app = (DiaryApp) getApplication();
        component = app.getAddContactComponent(this, this);
        presenter = getPresenter();
    }

    public AddContactPresenter getPresenter() {
        return this.component.getAddContactPresenter();
    }
}
