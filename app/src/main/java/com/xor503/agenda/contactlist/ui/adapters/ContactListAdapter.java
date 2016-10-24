package com.xor503.agenda.contactlist.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xor503.agenda.R;
import com.xor503.agenda.contactlist.ui.ContactListActivity;
import com.xor503.agenda.entities.Contact;
import com.xor503.agenda.showcontact.ShowContactActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xor503 on 10/13/16.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder>{
    private List<Contact> contactList;
    private OnItemClickListener onItemClickListener;
    private ContactListActivity activity;


    public ContactListAdapter(List<Contact> contactList, OnItemClickListener onItemClickListener, ContactListActivity activity) {
        this.contactList = contactList;
        this.onItemClickListener = onItemClickListener;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact currentContact = contactList.get(position);
        holder.txtContactName.setText(currentContact.getName());
        holder.txtContactPhone.setText(currentContact.getPhone());
        holder.setOnItemClickListener(currentContact, onItemClickListener);
    }

    public void setContacts(List<Contact> contacts) {
        this.contactList = contacts;
        notifyDataSetChanged();
    }

    public void removeContact(Contact contact) {
        contactList.remove(contact);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void showContact(Contact contact) {
        ShowContactActivity.createInstance(activity, contact);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgContact)
        ImageView imgContact;
        @BindView(R.id.txtContactName)
        TextView txtContactName;
        @BindView(R.id.txtContactPhone)
        TextView txtContactPhone;
        @BindView(R.id.imgShow)
        ImageButton imgShow;
        @BindView(R.id.imgDelete)
        ImageButton imgDelete;

        private View view;
        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Contact currentContact, final OnItemClickListener onItemClickListener){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(currentContact);
                }
            });
            imgShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onShowClick(currentContact);
                }
            });
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onDeleteClick(currentContact);
                }
            });
        }
    }
}
