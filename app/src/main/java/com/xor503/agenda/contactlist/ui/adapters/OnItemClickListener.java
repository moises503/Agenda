package com.xor503.agenda.contactlist.ui.adapters;

import com.xor503.agenda.entities.Contact;

/**
 * Created by xor503 on 10/13/16.
 */

public interface OnItemClickListener {
    void onItemClick(Contact contact);
    void onShowClick(Contact contact);
    void onDeleteClick(Contact contact);
}
