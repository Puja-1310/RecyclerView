package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class recyclerview extends AppCompatActivity {
    private List<Student> studentList;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private DataBaseH dataBaseH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        dataBaseH = new DataBaseH(this);

        studentList = new ArrayList<>();
        SQLiteDatabase db = dataBaseH.getReadableDatabase();
        Cursor cursor = db.query(DataBaseH.TABLE_NAME2, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int studentNameIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_NAME);
                int studentRollNoIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_ROLLNO);
                int studentDivisionIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_DIVISION);
                int studentAgeIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_AGE);
                int studentGenderIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_GENDER);
                int studentSurNameIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_SURNAME);
                int studentAddressIndex = cursor.getColumnIndex(dataBaseH.COLUMN_STUDENT_ADDRESS);


                String studentName = cursor.getString(studentNameIndex);
                String studentRollNo = cursor.getString(studentRollNoIndex);
                String studentDivision = cursor.getString(studentDivisionIndex);
                String studentAge = cursor.getString(studentAgeIndex);
                String studentGender = cursor.getString(studentGenderIndex);
                String studentSurName = cursor.getString(studentSurNameIndex);
                String studentAddress = cursor.getString(studentAddressIndex);

                Student student = new Student(studentName,studentRollNo,studentDivision ,studentAge, studentGender, studentSurName,studentAddress);
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        recyclerView = findViewById(R.id.recyclerView);
        studentAdapter = new StudentAdapter(studentList, new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student student) {
                String message = "Name: " + student.getStudent_name() + "\n" +
                        "Roll No: " + student.getStudent_rollNo() + "\n" +
                        "Division: " + student.getStudent_division() + "\n" +
                        "Age: " + student.getStudent_age() + "\n" +
                        "Gender: " + student.getStudent_gender() + "\n" +
                        "Address: " + student.getStudent_Address() + "\n" +
                        "Surname: " + student.getStudent_surName();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);
    }
}
