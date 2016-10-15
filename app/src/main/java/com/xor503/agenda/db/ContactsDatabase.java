package com.xor503.agenda.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by xor503 on 10/13/16.
 */
@Database(name = ContactsDatabase.NAME, version = ContactsDatabase.VERSION)
public class ContactsDatabase {
    public static final int VERSION = 3;
    public static final String NAME = "Contacts";
}
