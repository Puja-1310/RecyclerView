package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    private OnItemClickListener listener;

    public StudentAdapter(  List<Student> studentList, OnItemClickListener listener) {
        this.studentList = studentList;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void onItemClick(Student student);
    }


    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        Student student = studentList.get(position);

        holder.Student_nameTextView.setText(student.getStudent_name());
        holder.Student_rollNoTextView.setText(student.getStudent_rollNo());
        holder.Student_divisionTextView.setText(student.getStudent_division());
//        holder.Student_addressTextView.setText( student.getStudent_address());
        holder.Student_ageTextView.setText(student.getStudent_age());
        holder.Student_genderTextView.setText(student.getStudent_gender());
        holder.Student_surNameTextView.setText(student.getStudent_surName());
//        holder.Student_hometownTextView.setText(student.getStudent_hometown());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView Student_nameTextView;
        TextView Student_rollNoTextView;
        TextView Student_divisionTextView;
        TextView Student_addressTextView;
        TextView Student_ageTextView;
        TextView Student_genderTextView;
        TextView Student_surNameTextView;
//        TextView Student_hometownTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            Student_nameTextView = itemView.findViewById(R.id.Student_nameTextView);
            Student_rollNoTextView = itemView.findViewById(R.id.Student_rollNoTextView);
            Student_divisionTextView = itemView.findViewById(R.id.Student_divisionTextView);
            Student_ageTextView = itemView.findViewById(R.id.Student_ageTextView);
            Student_genderTextView = itemView.findViewById(R.id.Student_genderTextView);
            Student_surNameTextView = itemView.findViewById(R.id.Student_SurNameTextView);
//            Student_hometownTextView = itemView.findViewById(R.id.Student_HometownTextView);
        }
    }
}
