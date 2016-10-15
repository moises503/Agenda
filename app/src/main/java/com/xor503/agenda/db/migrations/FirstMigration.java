package com.xor503.agenda.db.migrations;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;
import com.xor503.agenda.db.ContactsDatabase;
import com.xor503.agenda.entities.Contact;

/**
 * Created by xor503 on 10/14/16.
 */
@Migration(version = ContactsDatabase.VERSION, database = ContactsDatabase.class)
public class FirstMigration extends AlterTableMigration<Contact> {

    public FirstMigration(Class<Contact> table) {
        super(table);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();
        addColumn(SQLiteType.TEXT, "last_name");
        addColumn(SQLiteType.TEXT, "fb");
        addColumn(SQLiteType.TEXT, "tweet");
    }
}
