package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class   DBHelper extends SQLiteOpenHelper {
    Context ctx;
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME1 = "Emp_Table";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_BIRTH_DATE = "Birth_Date";
    private static final String TEMP_TABLE_NAME = "demo_table";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME1 + " (" +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_BIRTH_DATE + " INTEGER  )";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
onCreate(db);
    }

    public long AddDetails(String name, String email, String birthDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_BIRTH_DATE, birthDate);
        long newRowId = db.insert(TABLE_NAME1, null, values);
        db.close();
        return newRowId;
    }

    public void DeleteColumn() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a temporary table without the column to be deleted
        String createTempTableQuery = "CREATE TABLE " + TEMP_TABLE_NAME + " (Name TEXT, Birth_Date INTEGER)";
        db.execSQL(createTempTableQuery);

        // Copy data from the original table to the temporary table, excluding the column to be deleted
        String copyDataQuery = "INSERT INTO " + TEMP_TABLE_NAME + " (Name, Birth_Date) SELECT Name, Birth_Date FROM " + TABLE_NAME1;
        db.execSQL(copyDataQuery);

        // Drop the original table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);

        // Rename the temporary table to the original table name
        db.execSQL("ALTER TABLE " + TEMP_TABLE_NAME + " RENAME TO " + TABLE_NAME1);

        db.close();
        Toast.makeText(ctx, "Colum Deleted", Toast.LENGTH_SHORT).show();

    }
}
