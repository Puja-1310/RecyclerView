package com.example.recyclerview;
import static com.example.recyclerview.DataBaseH.TABLE_NAME2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    private DataBaseH dataBaseH;
    EditText editTextStudentName ;
    EditText editTextStudentRollNo ;
    EditText editTextStudentDivision;
    EditText editTextStudentAge ;
    EditText editTextStudentGender ;
    EditText editTextStudentSurName ;
    EditText editTextStudentHometown;
    EditText editTextStudentAddress ;
    String Student_Name, Student_RollNo,Student_Division, Student_Address,Student_Age,Student_Gender,Student_SurName,Student_Hometown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        dataBaseH = new DataBaseH(this);

        Button AddStudentDetails = findViewById(R.id.buttonAddStudentDetails);
        AddStudentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddStudentDetails();
            }
        });

        Button GetAllStudentData = findViewById(R.id.buttonGetAllStudentData);
        GetAllStudentData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAllStudentData();
            }
        });
        Button UpdateStudentDetails = findViewById(R.id.buttonUpdateStudentDetails);
        UpdateStudentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStudentDetails();
            }
        });
        Button DeleteStudentData = findViewById(R.id.buttonDeleteStudentData);
        DeleteStudentData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteStudentData();
            }
        });

        Button DeletedColumn = findViewById(R.id.StudentDeleteColumn);
        DeletedColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseH.DeletedColumn();
            }
        });

        Button AddColumn = findViewById(R.id.StdAddColumn);
        AddColumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dataBaseH.AddColumn();
            }
        });
    }
    private void AddStudentDetails() {
        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentRollNo = findViewById(R.id.editTextStudentRollNo);
        editTextStudentDivision = findViewById(R.id.editTextStudentDivision);
        editTextStudentAddress = findViewById(R.id.editTextStudentAddress);
        editTextStudentAge = findViewById(R.id.editTextStudentAge);
        editTextStudentGender = findViewById(R.id.editTextStudentGender);
        editTextStudentSurName = findViewById(R.id.editTextStudentSurName);
        editTextStudentHometown = findViewById(R.id.editTextStudentHometown);

         Student_Name = editTextStudentName.getText().toString();
         Student_RollNo = editTextStudentRollNo.getText().toString();
         Student_Division = editTextStudentDivision.getText().toString();
         Student_Address = editTextStudentAddress.getText().toString();
         Student_Age = editTextStudentAge.getText().toString();
         Student_Gender = editTextStudentGender.getText().toString();
         Student_SurName = editTextStudentSurName.getText().toString();
         Student_Hometown = editTextStudentHometown.getText().toString();


        long newRowId = dataBaseH.AddStudentDetails ( Student_Name,Student_RollNo,Student_Division,Student_Address,Student_Age,Student_Gender,Student_SurName,Student_Hometown);
        if (newRowId != -1) {
            Toast.makeText(this, " Student Details inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error inserting Student details", Toast.LENGTH_SHORT).show();
        }
        editTextStudentName.setText("");
        editTextStudentRollNo.setText("");
        editTextStudentDivision.setText("");
        editTextStudentAddress.setText("");
        editTextStudentAge.setText("");
        editTextStudentGender.setText("");
        editTextStudentSurName.setText("");
        editTextStudentHometown.setText("");
    }

    private void GetAllStudentData() {
//        SQLiteDatabase db = dataBaseH.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME2, null, null, null, null, null, null);
//
//        ArrayList<String> studentList = new ArrayList<>();
//        int Student_NameColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_NAME);
//        int Student_RollNoColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_ROLLNO);
//        int Student_DivisionColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_DIVISION);
//        int Student_AddressColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_ADDRESS);
//        int Student_AgeColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_AGE);
//        int Student_GenderColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_GENDER);
//        int Student_SurNameColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_SURNAME);
//        int Student_HometownColumnIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_HOMETOWN);
//
//        while (cursor.moveToNext()) {
//            String Student_Name = (Student_NameColumnIndex != -1) ? cursor.getString(Student_NameColumnIndex) : "N/A";
//            String Student_RollNo = (Student_RollNoColumnIndex != -1) ? cursor.getString(Student_RollNoColumnIndex) : "N/A";
//            String Student_Division = (Student_DivisionColumnIndex != -1) ? cursor.getString(Student_DivisionColumnIndex) : "N/A";
//            String Student_Address = (Student_AddressColumnIndex != -1) ? cursor.getString(Student_AddressColumnIndex) : "N/A";
//            String Student_Age = (Student_AgeColumnIndex != -1) ? cursor.getString(Student_AgeColumnIndex) : "N/A";
//            String Student_Gender = (Student_GenderColumnIndex != -1) ? cursor.getString(Student_GenderColumnIndex) : "N/A";
//            String Student_SurName = (Student_SurNameColumnIndex != -1) ? cursor.getString(Student_SurNameColumnIndex) : "N/A";
//            String Student_Hometown = (Student_HometownColumnIndex != -1) ? cursor.getString(Student_HometownColumnIndex) : "N/A";
//
//            String studentDetails =
//                                    "Student_Name: " + Student_Name +
//                                    ", Student_RollNo: " + Student_RollNo +
//                                    ", Student_Division: "+ Student_Division +
//                                    ", Student_Address: " + Student_Address +
//                                    ", Student_Age: " + Student_Age +
//                                    ", Student_Gender: " + Student_Gender +
//                                    ", Student_SurName: " + Student_SurName +
//                                    ", Student_Hometown: " + Student_Hometown;
//
//            studentList.add(studentDetails);
//        }
//        cursor.close();
//        db.close();

        Intent intent = new Intent(this, recyclerview.class);
//        intent.putStringArrayListExtra("studentList", studentList);
        startActivity(intent);
    }

//        Toast.makeText(this, AllStudentDetails.toString(), Toast.LENGTH_LONG).show();

    private void UpdateStudentDetails() {
        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentRollNo = findViewById(R.id.editTextStudentRollNo);
        editTextStudentDivision = findViewById(R.id.editTextStudentDivision);
        editTextStudentAge = findViewById(R.id.editTextStudentAge);
        editTextStudentGender = findViewById(R.id.editTextStudentGender);
        editTextStudentSurName = findViewById(R.id.editTextStudentSurName);
        editTextStudentHometown = findViewById(R.id.editTextStudentHometown);
        editTextStudentAddress = findViewById(R.id.editTextStudentAddress);


        Student_Name = editTextStudentName.getText().toString();
        Student_RollNo = editTextStudentRollNo.getText().toString();
        Student_Division = editTextStudentDivision.getText().toString();
        Student_Age = editTextStudentAge.getText().toString();
        Student_Gender = editTextStudentGender.getText().toString();
        Student_SurName = editTextStudentSurName.getText().toString();

//
        SQLiteDatabase db = dataBaseH.getWritableDatabase();
        ContentValues values = new ContentValues();

//        values.put(dataBaseH.COLUMN_STUDENT_NAME,Student_Name);
        values.put(dataBaseH.COLUMN_STUDENT_ROLLNO,Student_RollNo);
        values.put(dataBaseH.COLUMN_STUDENT_DIVISION,Student_Division);
        values.put(dataBaseH.COLUMN_STUDENT_AGE, Student_Age);
        values.put(dataBaseH.COLUMN_STUDENT_GENDER, Student_Gender);
        values.put(dataBaseH.COLUMN_STUDENT_SURNAME,Student_SurName);


        // Check if the roll number already exists in the table
        String[] columns = {dataBaseH.COLUMN_STUDENT_NAME};
        String selection =dataBaseH.COLUMN_STUDENT_NAME + "=?";
        String[] selectionArgs = {Student_Name};
        Cursor cursor = db.query(TABLE_NAME2, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            // Roll number exists, update the row
            db.update(TABLE_NAME2, values, selection, selectionArgs);
            Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Roll number doesn't exist, insert a new row
            values.put(dataBaseH.COLUMN_STUDENT_NAME,Student_Name);
            db.insert(TABLE_NAME2, null, values);
            Toast.makeText(this, "data inserted successfully", Toast.LENGTH_SHORT).show();
        }

        cursor.close();



////        String whereClause = dataBaseH.COLUMN_STUDENT_ADDRESS + " = ?" +  dataBaseH.COLUMN_STUDENT_HOMETOWN + " = ?";
//        String whereClause = dataBaseH.COLUMN_STUDENT_NAME + " = ? ";
//
//        String[] whereArgs = {Student_Name};
//
////        int rowsUpdated = db.update(TABLE_NAME2, values, whereClause, whereArgs);
////        db.close();
//        int rowsUpdated = 0;
//
//        if (whereArgs.length == 2) {
//            rowsUpdated  = db.update(TABLE_NAME2, values, whereClause, whereArgs);
//            Toast.makeText(this, "updated successfully", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Error updating ", Toast.LENGTH_SHORT).show();
//        }
        editTextStudentName.setText("");
        editTextStudentRollNo.setText("");
        editTextStudentDivision.setText("");
        editTextStudentAge.setText("");
        editTextStudentGender.setText("");
        editTextStudentSurName.setText("");
    }
    private void DeleteStudentData() {
        SQLiteDatabase db = dataBaseH.getWritableDatabase();
        db.delete(TABLE_NAME2,null,null);
        db.close();

        Toast.makeText(this, "All Data Deleted", Toast.LENGTH_SHORT).show();
    }
}