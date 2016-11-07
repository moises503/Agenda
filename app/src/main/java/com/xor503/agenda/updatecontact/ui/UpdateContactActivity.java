package com.xor503.agenda.updatecontact.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xor503.agenda.DiaryApp;
import com.xor503.agenda.R;
import com.xor503.agenda.addcontact.ui.AddContactActivity;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.showcontact.ShowContactActivity;
import com.xor503.agenda.updatecontact.UpdateContactPresenter;
import com.xor503.agenda.updatecontact.di.UpdateContactComponent;

import java.io.File;
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
    @BindView(R.id.btnUpdateContact)
    Button btnUpdateContact;



    private UpdateContactComponent component;
    private UpdateContactPresenter presenter;

    private static String APP_DIRECTORY = "Agenda/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "MyContacts";

    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private final int SELECT_PICTURE = 300;

    private Contact contact;
    private List<String> data;

    private String mPath;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);
        ButterKnife.bind(this);
        data = new ArrayList<String>();
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
        if(contact.getImagePath() != null){
            if(contact.getImagePath().contains("content")){
                setImageParalax(Uri.parse(contact.getImagePath()));
            } else {
                setImageParalax(contact.getImagePath());
            }
        }
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
        data.add(txtName.getText().toString());
        data.add(txtLastName.getText().toString());
        data.add(txtPhone.getText().toString());
        data.add(txtEmail.getText().toString());
        data.add(txtFb.getText().toString());
        data.add(txtTweet.getText().toString());
        if(getImagePath() == null){
            data.add(contact.getImagePath());
        }else {
            data.add(getImagePath());
        }
        presenter.updateContact(data, contact);
    }

    @OnClick(R.id.updateImage)
    public void onUpdateImage(){
        showOptions();
    }

    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(UpdateContactActivity.this);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    openCamera();
                }else if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                }
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();

        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() / 1000;
            String imageName = timestamp.toString() + ".jpg";

            mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                    + File.separator + imageName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, PHOTO_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:
                    setImageParalax(getmPath());
                    imagePath = getmPath();
                    break;
                case SELECT_PICTURE:
                    Uri path = data.getData();
                    /*if(path.toString().contains("content")){
                        setImageParalax(Uri.parse(path.toString()));
                        Toast.makeText(getApplicationContext(), path.toString(), Toast.LENGTH_LONG).show();
                    }*/
                    setImageParalax(path);
                    imagePath = path.toString();
                    break;

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ShowContactActivity.createInstance(this, contact);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getmPath() {
        return mPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public UpdateContactPresenter getPresenter() {
        return this.component.getUpdateContactPresenter();
    }

    public void setImageParalax(Uri path) {
        Glide.with(this).load(path).centerCrop().into(imageParalax);
    }

    public void setImageParalax(String path) {
        Glide.with(this).load(path).centerCrop().into(imageParalax);
    }
}
