package com.example.recyclerview;

import static com.example.recyclerview.DataBaseH.TABLE_NAME2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplayStudentsActivity extends AppCompatActivity {
    private ListView listViewStudents;
    private DataBaseH dataBaseH;
    private ArrayList<String> studentList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_students);

        listViewStudents = findViewById(R.id.listViewStudents);
        studentList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);

        listViewStudents.setAdapter(adapter);

        Intent intent = getIntent();
        ArrayList<String> studentList = intent.getStringArrayListExtra("studentList");
        if (studentList != null){
            adapter.addAll(studentList);
        }
    }
    
}


