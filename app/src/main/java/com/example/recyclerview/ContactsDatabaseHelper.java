package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class ContactsDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    public ContactsDatabaseHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_PHONE_NUMBER + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addContact(String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int idColumnIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME);
            int phoneNumberColumnIndex = cursor.getColumnIndex(COLUMN_PHONE_NUMBER);

            int id = -1;
            String name = "";
            String phoneNumber = "";

            if (idColumnIndex != -1) {
                id = cursor.getInt(idColumnIndex);
            }
            if (nameColumnIndex != -1) {
                name = cursor.getString(nameColumnIndex);
            }
            if (phoneNumberColumnIndex != -1) {
                phoneNumber = cursor.getString(phoneNumberColumnIndex);
            }

            Contact contact = new Contact(id, name, phoneNumber);
            contactList.add(contact);
        }

        cursor.close();
        db.close();
        return contactList;
    }
    public int updateContact(int contactId, String name, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = { String.valueOf(contactId) };
        int rowsAffected = db.update(TABLE_NAME, values, selection, selectionArgs);
        db.close();
        return rowsAffected;
    }
    public int deleteContact(int contactId){
         SQLiteDatabase db = this.getWritableDatabase();
         String selection = COLUMN_ID + "= ?";
         String[] selectionArgs = {String.valueOf(contactId)};
         int rowsAffected = db.delete(TABLE_NAME,selection,selectionArgs);
         db.close();
         return rowsAffected;
    }

}
