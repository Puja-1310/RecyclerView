package com.example.recyclerview;

public class Student {
    private String student_name,student_rollNo,student_division,student_age,student_gender,student_surName,student_Address;

    public Student(String student_name, String student_rollNo, String student_division,String student_age, String student_gender, String student_surName,String student_Address) {
        this.student_name = student_name;
        this.student_rollNo = student_rollNo;
        this.student_division = student_division;
        this.student_age = student_age;
        this.student_gender = student_gender;
        this.student_surName = student_surName;
        this.student_Address = student_Address;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_rollNo() {
        return student_rollNo;
    }

    public String getStudent_division() {
        return student_division;
    }

    public String getStudent_age() {
        return student_age;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public String getStudent_surName() {
        return student_surName;
    }

    public String getStudent_Address() {
        return student_Address;
    }
}
