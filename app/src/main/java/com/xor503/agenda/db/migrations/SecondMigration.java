package com.xor503.agenda.db.migrations;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;
import com.xor503.agenda.db.ContactsDatabase;
import com.xor503.agenda.entities.Contact;

/**
 * Created by moises on 04/11/16.
 */
@Migration(version = ContactsDatabase.VERSION, database = ContactsDatabase.class)
public class SecondMigration extends AlterTableMigration<Contact> {
    public SecondMigration(Class<Contact> table) {
        super(table);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();
        addColumn(SQLiteType.TEXT, "image_path");
    }
}
