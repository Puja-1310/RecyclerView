package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DataBaseH extends SQLiteOpenHelper {
     Context ctx;
    private static final String DATABASE_NAME = "DataBase1.db";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME2 = "STUDENT_Table";
    public static final String COLUMN_STUDENT_NAME = "Student_Name";
    public static final String COLUMN_STUDENT_ROLLNO = "Student_RollNo";
    public static final String COLUMN_STUDENT_DIVISION = "Student_Division";
    public static final String COLUMN_STUDENT_ADDRESS = "Student_Address";
    public static final String COLUMN_STUDENT_AGE = "Student_Age";
    public static final String COLUMN_STUDENT_GENDER = "Student_Gender";
    public static final String COLUMN_STUDENT_SURNAME = "Student_SurName";
    public static final String COLUMN_STUDENT_HOMETOWN = "Student_Hometown";
    private static final String TEMP_TABLE_NAME1 = "New_Temp_Table";
    public DataBaseH( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME2 + " (" +
                COLUMN_STUDENT_NAME + " TEXT, " +
                COLUMN_STUDENT_ROLLNO + " INTEGER, " +
                COLUMN_STUDENT_DIVISION + " TEXT, " +
                COLUMN_STUDENT_ADDRESS + " TEXT, " +
                COLUMN_STUDENT_AGE + " INTEGER, " +
                COLUMN_STUDENT_GENDER + " TEXT, " +
                COLUMN_STUDENT_SURNAME + " TEXT, " +
                COLUMN_STUDENT_HOMETOWN + " TEXT  )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public long AddStudentDetails(String Student_Name, String Student_RollNo, String Student_Division, String student_Address, String student_Age, String student_Gender, String student_SurName, String student_Hometown) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME,Student_Name);
        values.put(COLUMN_STUDENT_ROLLNO, Student_RollNo);
        values.put(COLUMN_STUDENT_DIVISION, Student_Division);
        values.put(COLUMN_STUDENT_ADDRESS,student_Address );
        values.put(COLUMN_STUDENT_AGE,student_Age );
        values.put(COLUMN_STUDENT_GENDER,student_Gender );
        values.put(COLUMN_STUDENT_SURNAME,student_SurName );
        values.put(COLUMN_STUDENT_HOMETOWN,student_Hometown);

        long newRowId = db.insert(TABLE_NAME2, null, values);
        db.close();
        return newRowId;
    }
    public void DeletedColumn() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Create a temporary table without the column to be deleted
        String createTempTableQuery = "CREATE TABLE " + TEMP_TABLE_NAME1 + " (Student_Name TEXT, Student_RollNo INTEGER PRIMARY KEY, Student_Address TEXT, Student_Age INTEGER , Student_Gender TEXT, Student_Hometown TEXT)";
        db.execSQL(createTempTableQuery);

        // Copy data from the original table to the temporary table, excluding the column to be deleted
        String copyDataQuery = "INSERT INTO " + TEMP_TABLE_NAME1 + " (Student_Name, Student_RollNo, Student_Address, Student_Age, Student_Gender, Student_Hometown) SELECT Student_Name, Student_RollNo, Student_Address,Student_Age, Student_Gender,Student_Hometown FROM " + TABLE_NAME2;
        db.execSQL(copyDataQuery);

        // Drop the original table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);

        // Rename the temporary table to the original table name
        db.execSQL("ALTER TABLE " + TEMP_TABLE_NAME1 + " RENAME TO " + TABLE_NAME2);

        db.close();
        Toast.makeText(ctx, "Colum Deleted", Toast.LENGTH_SHORT).show();
    }

    public void AddColumn() {
        SQLiteDatabase db = this.getWritableDatabase();

//        String alterTableQuery1 = "ALTER TABLE " + TABLE_NAME2 + " ADD COLUMN Student_Age INTEGER";
//        String alterTableQuery2 = "ALTER TABLE " + TABLE_NAME2 + " ADD COLUMN Student_Gender TEXT";
        String alterTableQuery3 = "ALTER TABLE " + TABLE_NAME2 + " ADD COLUMN Student_SurName TEXT";
        String alterTableQuery4 = "ALTER TABLE " + TABLE_NAME2 + " ADD COLUMN Student_Hometown TEXT";
        String alterTableQuery5 = "ALTER TABLE " + TABLE_NAME2 + " ADD COLUMN Student_info TEXT";


//        db.execSQL(alterTableQuery1);
//        db.execSQL(alterTableQuery2);
        db.execSQL(alterTableQuery3);
        db.execSQL(alterTableQuery4);
        db.execSQL(alterTableQuery5);

        db.close();
        Toast.makeText(ctx, "Columns Added", Toast.LENGTH_SHORT).show();
    }
}
