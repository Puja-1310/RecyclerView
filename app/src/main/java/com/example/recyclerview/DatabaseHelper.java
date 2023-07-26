package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context ctx;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "user_details";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_MOBILE_NUMBER = "user_mobile";
    public static final String COLUMN_LOCATION = "user_location";
    private static final String TEMP_TABLE_NAME = "temporary_table";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_MOBILE_NUMBER + " TEXT, " +
                COLUMN_LOCATION + " TEXT)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public long addUserDetails(String name, String Mobile, String userLocation) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME,name);
        values.put(COLUMN_MOBILE_NUMBER, Mobile);
        values.put(COLUMN_LOCATION, userLocation);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    public void deleteColumn() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a temporary table without the column to be deleted
        String createTempTableQuery = "CREATE TABLE " + TEMP_TABLE_NAME + " (id INTEGER PRIMARY KEY, user_location TEXT, user_mobile INTEGER)";
        db.execSQL(createTempTableQuery);

        // Copy data from the original table to the temporary table, excluding the column to be deleted
        String copyDataQuery = "INSERT INTO " + TEMP_TABLE_NAME + " (id, user_location, user_mobile) SELECT id, user_location, user_mobile FROM " + TABLE_NAME;
        db.execSQL(copyDataQuery);

        // Drop the original table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Rename the temporary table to the original table name
        db.execSQL("ALTER TABLE " + TEMP_TABLE_NAME + " RENAME TO " + TABLE_NAME);

        db.close();
        Toast.makeText(ctx, "Colum Deleteted", Toast.LENGTH_SHORT).show();
    }

//    public Cursor getAllUserDetails(){
//        SQLiteDatabase db = getReadableDatabase();
//        return db.query(TABLE_NAME,null,null,null,null,null,null);
//    }
//
//    public int updateUserDetails(int userId,String Name,String Mobile){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME,Name);
//        values.put(COLUMN_MOBILE_NUMBER,Mobile);
//        String whereClause = COLUMN_ID + " = ?";
//        String[] whereArgs = {String.valueOf(userId)};
//        int rowUpdated = db.update(TABLE_NAME,values,whereClause,whereArgs);
//        db.close();
//        return rowUpdated;
//    }
//
//    public int deleteUserDetails(int userId){
//        SQLiteDatabase db = getWritableDatabase();
//        String whereClause = COLUMN_ID + " = ?";
//        String[] whereArgs = {String.valueOf(userId)};
//        int rowDeleted = db.delete(TABLE_NAME,whereClause,whereArgs);
//        db.close();
//        return rowDeleted;
//    }

    }

