package com.xor503.agenda.contactlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.xor503.agenda.DiaryApp;
import com.xor503.agenda.R;
import com.xor503.agenda.addcontact.ui.AddContactActivity;
import com.xor503.agenda.contactlist.ContactListPresenter;
import com.xor503.agenda.contactlist.di.ContactListComponent;
import com.xor503.agenda.contactlist.ui.adapters.ContactListAdapter;
import com.xor503.agenda.contactlist.ui.adapters.OnItemClickListener;
import com.xor503.agenda.entities.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity implements ContactListView, OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ContactListAdapter adapter;
    private ContactListPresenter presenter;
    private ContactListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();

        presenter.onCreate();
        presenter.getContacts();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getContacts();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.getContacts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContacts(List<Contact> data) {
        adapter.setContacts(data);
    }

    @Override
    public void contactUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void contactDeleted(Contact contact) {
        adapter.removeContact(contact);
    }

    @Override
    public void onItemClick(Contact contact) {

    }

    @Override
    public void onShowClick(Contact contact) {
        adapter.showContact(contact);
    }

    @Override
    public void onDeleteClick(Contact contact) {
        presenter.removeContact(contact);
    }

    @OnClick(R.id.fabAddContact)
    public void onAddContactToList(){
        startActivity(new Intent(this, AddContactActivity.class));
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupInjection(){
        DiaryApp app = (DiaryApp) getApplication();
        component = app.getContactListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    public ContactListPresenter getPresenter() {
        return component.getContactListPresenter();
    }

    public ContactListAdapter getAdapter() {
        return component.getAdapter();
    }
}
