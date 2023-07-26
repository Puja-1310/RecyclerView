package com.example.recyclerview;

import static com.example.recyclerview.DatabaseHelper.TABLE_NAME;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        Button addUserDetailsButton = findViewById(R.id.buttonAddUserDetails);
        addUserDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserDetails();
            }
        });

        Button getAllUserDetailsButton = findViewById(R.id.buttonGetAllUserDetails);
        getAllUserDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllUserDetails();
            }
        });

        Button updateUserDetailsButton = findViewById(R.id.buttonUpdateUserDetails);
        updateUserDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserDetails();
            }
        });


        Button deleteUserDetailsButton = findViewById(R.id.buttonDeleteUserDetails);
        deleteUserDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserDetails();

            }
        });///CRUD operation is upto here.....
        Button deleteColumn = findViewById(R.id.deleteColumn);
        deleteColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteColumn();

            }
        });
    }

    private void addUserDetails() {
        EditText userNameEditText = findViewById(R.id.editTextUserName);
        EditText mobileNumberEditText = findViewById(R.id.editTextMobileNumber);
        EditText userLocationEditText = findViewById(R.id.editTextUserLocation);

        String userName = userNameEditText.getText().toString();
        String mobileNumber = mobileNumberEditText.getText().toString();
        String userLocation = userLocationEditText.getText().toString();

        long newRowId = databaseHelper.addUserDetails( userName,mobileNumber,userLocation);
        if (newRowId != -1) {
            Toast.makeText(this, "User Details inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error inserting user details", Toast.LENGTH_SHORT).show();
        }

        userNameEditText.setText("");
        mobileNumberEditText.setText("");
        userLocationEditText.setText("");
    }

    private void getAllUserDetails() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        StringBuilder userDetails = new StringBuilder();
        int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
        int userNameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME);
        int mobileNumberColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_MOBILE_NUMBER);
        int userLocationColumnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LOCATION);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idColumnIndex);
            String userName = (userNameColumnIndex != -1) ? cursor.getString(userNameColumnIndex) : "N/A";
            String mobileNumber = (mobileNumberColumnIndex != -1) ? cursor.getString(mobileNumberColumnIndex) : "N/A";
            String userLocation = (userLocationColumnIndex != -1) ? cursor.getString(userLocationColumnIndex) : "N/A";
            userDetails.append("ID: ").append(id)
                    .append(", User Name: ").append(userName)
                    .append(", Mobile Number: ").append(mobileNumber)
                    .append(", User Location: ").append(userLocation)
                    .append("\n");
        }
        cursor.close();
        db.close();

        Toast.makeText(this, userDetails.toString(), Toast.LENGTH_LONG).show();
    }

    private void updateUserDetails() {
        EditText userNameEditText  = findViewById(R.id.editTextUserName);
        EditText userLocationEditText = findViewById(R.id.editTextUserLocation);
        String userName = userNameEditText.getText().toString();
        String userLocation = userLocationEditText.getText().toString();
        String mobileNumber = "123456789";

//        EditText mobileNumberEditText = findViewById(R.id.editTextMobileNumber);
//        String mobileNumber = mobileNumberEditText.getText().toString();
//        String userName = "puja pawar"; // Update the user name to "puja"

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(DatabaseHelper.COLUMN_MOBILE_NUMBER, mobileNumber);
//        values.put(DatabaseHelper.COLUMN_USER_NAME,userName);

//        String whereClause = DatabaseHelper.COLUMN_USER_NAME + " = ?";
        String whereClause = DatabaseHelper.COLUMN_MOBILE_NUMBER + " = ?";
        String[] whereArgs = {mobileNumber}; // Use the user name variable

        int rowsUpdated = db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();

        if (rowsUpdated > 0) {
            Toast.makeText(this, "Mobile number updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error updating mobile number", Toast.LENGTH_SHORT).show();
        }
//        userNameEditText.setText("");
        userLocationEditText.setText("");
    }


    private void deleteUserDetails() {
//        EditText userNameEditText = findViewById(R.id.editTextUserName);
//        String userName = userNameEditText.getText().toString();
//        String mobileNumber = "123456789";

//        EditText mobileNumberEditText = findViewById(R.id.editTextMobileNumber);
//        String mobileNumber = mobileNumberEditText.getText().toString();

//        String userName = "riya";

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.close();

        Toast.makeText(this, "All Data Deleted", Toast.LENGTH_SHORT).show();
//        String whereClause = DatabaseHelper.COLUMN_MOBILE_NUMBER + " = ?";
//        String[] whereArgs = {mobileNumber};

//        int rowsDeleted = db.delete(DatabaseHelper.TABLE_NAME, whereClause, whereArgs);

//        if (rowsDeleted > 0) {
//            Toast.makeText(this, "User details deleted successfully", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Error deleting user details", Toast.LENGTH_SHORT).show();
//        }
    }
}

