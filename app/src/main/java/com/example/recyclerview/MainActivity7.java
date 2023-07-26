package com.example.recyclerview;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity {
    private Button buttonA, buttonB;
    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
//        ExampleFragment fragment = ExampleFragment.newInstance("example text",123);
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
        buttonA = findViewById(R.id.button_a);
        buttonB = findViewById(R.id.button_b);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragmentFullscreen(fragmentA);
                buttonA.setEnabled(false);
                buttonB.setEnabled(true);
                Toast.makeText(MainActivity7.this, "Button A clicked", Toast.LENGTH_SHORT).show();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragmentFullscreen(fragmentB);
                buttonA.setEnabled(true);
                buttonB.setEnabled(false);
                fragmentB.updateEditText("Text updated from MainActivity7");
                Toast.makeText(MainActivity7.this,"Button B clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void openFragmentFullscreen(Fragment fragment) {
        fragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }
}
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container_a,fragmentA)
//                .replace(R.id.container_b,fragmentB)
//               .commit();
//    @Override
//    public void onInputASent(CharSequence input) {
//        fragmentB.updateEditText(input);
//    }
//    @Override
//    public void onInputBSent(CharSequence input) {
//        fragmentA.updateEditText(input);
//    }
