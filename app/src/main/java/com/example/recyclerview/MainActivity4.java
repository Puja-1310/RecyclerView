package com.example.recyclerview;

import static com.example.recyclerview.DBHelper.TABLE_NAME1;
import static com.example.recyclerview.DatabaseHelper.TABLE_NAME;
import static com.example.recyclerview.R.id.buttonDeleteColumn;
import static com.example.recyclerview.R.id.deleteColumn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
  private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new DBHelper(this);

        Button AddDetails = findViewById(R.id.buttonAddDetails);
        AddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDetails();
            }
        });

        Button GetAllData = findViewById(R.id.buttonGetAllData);
        GetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllData();
            }
        });

        Button UpdateDetails = findViewById(R.id.buttonUpdateDetails);
        UpdateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDetails();
            }
        });

        Button DeleteData = findViewById(R.id.buttonDeleteData);
        DeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }
        });

        Button DeleteColumn = findViewById(R.id.buttonDeleteColumn);
        DeleteColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dbHelper.DeleteColumn();
            }
        });
    }

    private void AddDetails() {
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextBirthDate = findViewById(R.id.editTextBirthDate);

        String Name = editTextName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String BirthDate = editTextBirthDate.getText().toString();

        long newRowId = dbHelper.AddDetails ( Name,Email,BirthDate);
        if (newRowId != -1) {
            Toast.makeText(this, " Details inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error inserting  details", Toast.LENGTH_SHORT).show();
        }

        editTextName.setText("");
        editTextEmail.setText("");
        editTextBirthDate.setText("");
    }

    private void GetAllData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME1, null, null, null, null, null, null);

        StringBuilder AllDetails = new StringBuilder();
        int NameColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
        int EmailColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_EMAIL);
        int BirthDateColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_BIRTH_DATE);

        while (cursor.moveToNext()) {
            String Name = (NameColumnIndex != -1) ? cursor.getString(NameColumnIndex) : "N/A";
            String Email = (EmailColumnIndex != -1) ? cursor.getString(EmailColumnIndex) : "N/A";
            String Birth_Date = (BirthDateColumnIndex != -1) ? cursor.getString(BirthDateColumnIndex) : "N/A";
            AllDetails
                    .append(",Name: ").append(Name)
                    .append(",Email: ").append(Email)
                    .append(",Birth_Date: ").append(Birth_Date)
                    .append("\n");
        }
        cursor.close();
        db.close();

        Toast.makeText(this, AllDetails.toString(), Toast.LENGTH_LONG).show();

//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
//
//        StringBuilder AllData = new StringBuilder();
//        int NameColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_NAME);
//        int EmailColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_EMAIL);
//        int BirthDateColumnIndex = cursor.getColumnIndex(DBHelper.COLUMN_BIRTH_DATE);
//
//        while (cursor.moveToNext()){
//            String Name = (NameColumnIndex != -1) ? cursor.getString(NameColumnIndex) : "N/A";
//            String Email = (EmailColumnIndex != -1) ? cursor.getString(EmailColumnIndex) : "N/A";
//            String Birth_Date = (BirthDateColumnIndex != -1) ? cursor.getString(BirthDateColumnIndex) : "N/A";
//            AllData.append(", Name: ").append(Name)
//                    .append(", Email: ").append(Email)
//                    .append(", Birth_Date: ").append(Birth_Date)
//                    .append("\n");
//        }
//        cursor.close();
//        db.close();
//
//        Toast.makeText(this, AllData.toString(), Toast.LENGTH_LONG).show();
    }

    private void UpdateDetails() {
        EditText editTextName  = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextBirthDate = findViewById(R.id.editTextBirthDate);

        String Name = editTextName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Birth_Date = "13/10/2000";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_NAME,Name);
        values.put(DBHelper.COLUMN_EMAIL,Email);

        String whereClause = DBHelper.COLUMN_BIRTH_DATE + " = ?";
        String[] whereArgs = {Birth_Date};

        int rowsUpdated = db.update(TABLE_NAME1, values, whereClause, whereArgs);
        db.close();

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Birth Date updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error updating Birth Date", Toast.LENGTH_SHORT).show();
        }
        editTextEmail.setText("");
        editTextName.setText("");
    }

    private void DeleteData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME1,null,null);
        db.close();

        Toast.makeText(this, "All Data Deleted", Toast.LENGTH_SHORT).show();
    }
}
