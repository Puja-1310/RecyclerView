package com.example.recyclerview;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    private TextView textView;
    private Context fragmentContext;
    //    private FragmentAListener listener;
//    private EditText editText;
//    private Button buttonOk;
//    public interface FragmentAListener{
//        void onInputASent(CharSequence input);
//    }
//    @Nullable
    public FragmentA() {
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        fragmentContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentContext = null;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);
        textView = rootView.findViewById(R.id.textView);
        return rootView;
    }
}

//    public void updateEditText(CharSequence input){
//        if (textView != null){
//            textView.setText(input);
//        }




//
////        editText = v.findViewById(R.id.edit_text);
////        buttonOk = v.findViewById(R.id.button_ok);
//        buttonOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CharSequence input = editText.getText();
//                listener.onInputASent(input);
//            }
//        });
//        return v;
//    }
//    public void updateEditText(CharSequence newText){
//        editText.setText(newText);
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        if (context instanceof FragmentAListener){
//            listener = (FragmentAListener) context;
//        }else {
//            throw new RuntimeException(context.toString()
//            + "must implement FragmentAListener");
//        }
//    }
//    public void onDetach(){
//        super.onDetach();
//        listener = null;
//    }

