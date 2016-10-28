package com.xor503.agenda.updatecontact.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xor503.agenda.DiaryApp;
import com.xor503.agenda.R;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.updatecontact.UpdateContactPresenter;
import com.xor503.agenda.updatecontact.di.UpdateContactComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateContactActivity extends AppCompatActivity implements UpdateContactView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
    @BindView(R.id.image_paralax)
    ImageView imageParalax;
    @BindView(R.id.collapser)
    CollapsingToolbarLayout collapser;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.btnUpdateContact)
    Button btnUpdateContact;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;


    private UpdateContactComponent component;
    private UpdateContactPresenter presenter;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        ButterKnife.bind(this);
        contact = (Contact) getIntent().getExtras().getSerializable("contact");
        setupToolbar();
        setupCollapsingToolbarLayout();
        showInfo();
        setupInjection();
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        DiaryApp app = (DiaryApp) getApplication();
        component = app.getUpdateContactComponent(this, this);
        presenter = getPresenter();
    }

    private void showInfo() {
        txtName.setText(contact.getName());
        txtLastName.setText(contact.getLastName());
        txtPhone.setText(contact.getPhone());
        txtEmail.setText(contact.getEmail());
        txtFb.setText(contact.getFb());
        txtTweet.setText(contact.getTweet());
    }

    private void setupCollapsingToolbarLayout() {
        collapser.setTitle(contact.getName() + " " + contact.getLastName());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void showMessage(String message, Contact contact) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.putExtra("contact", contact);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.btnUpdateContact)
    public void onClick() {
        List<String> data = new ArrayList<String>();
        data.add(txtName.getText().toString());
        data.add(txtLastName.getText().toString());
        data.add(txtPhone.getText().toString());
        data.add(txtEmail.getText().toString());
        data.add(txtFb.getText().toString());
        data.add(txtTweet.getText().toString());
        presenter.updateContact(data, contact);
    }

    public UpdateContactPresenter getPresenter() {
        return this.component.getUpdateContactPresenter();
    }
}
