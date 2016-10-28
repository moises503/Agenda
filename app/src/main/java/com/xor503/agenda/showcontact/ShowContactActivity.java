package com.xor503.agenda.showcontact;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xor503.agenda.R;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.updatecontact.ui.UpdateContactActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowContactActivity extends AppCompatActivity {
    public static final int CONTACT_REQUEST_CODE = 1;
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.image_paralax)
    ImageView imageParalax;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapser)
    CollapsingToolbarLayout collapser;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;
    @BindView(R.id.showPhone)
    TextView showPhone;
    @BindView(R.id.showEmail)
    TextView showEmail;
    @BindView(R.id.showFacebook)
    TextView showFacebook;
    @BindView(R.id.showTwitter)
    TextView showTwitter;

    private Contact contact;

    public static void createInstance(Activity activity, Contact contact) {
        Intent intent = getLaunchIntent(activity, contact);
        activity.startActivity(intent);
    }

    public static Intent getLaunchIntent(Context context, Contact contact) {
        Intent intent = new Intent(context, ShowContactActivity.class);
        intent.putExtra("contact", contact);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        ButterKnife.bind(this);
        setupContact();
        setupToolbar();
        setupCollapsingToolbarLayout();
        showInfo();
    }

    private void showInfo() {
        showPhone.setText(contact.getPhone());
        showEmail.setText(contact.getEmail());
        showFacebook.setText(contact.getFb());
        showTwitter.setText(contact.getTweet());
    }

    private void setupContact() {
        contact = (Contact) getIntent().getExtras().getSerializable("contact");
    }

    private void setupCollapsingToolbarLayout() {
        collapser.setTitle(contact.getName() + " " + contact.getLastName());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.fab)
    public void onUpdateContact() {
        /*Intent intent = new Intent(this, UpdateContactActivity.class);
        intent.putExtra("contact",contact);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(intent, CONTACT_REQUEST_CODE);*/
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhone())));
       }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "No se pudo lanzar la llamada "+ex.getMessage(), Toast.LENGTH_LONG);
       }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CONTACT_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                contact = (Contact) data.getExtras().getSerializable("contact");
                setupCollapsingToolbarLayout();
                showInfo();
            }
        }
    }
}
