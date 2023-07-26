package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomNavigation extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        menu.findItem(R.id.navigation_home).setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment1).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item ) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        int size = bottomNavigationView.getMenu().size();
//        for (int i = 0; i < size; i++) {
//            MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
//            menuItem.setIcon(null);
//        }

        if (item.getItemId() == R.id.navigation_home) {
            fragmentTransaction.replace(R.id.flFragment,fragment1);
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.navigation_dashboard) {
            fragmentTransaction.replace(R.id.flFragment, fragment2);
            Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.navigation_notifications) {
            fragmentTransaction.replace(R.id.flFragment, fragment3);
            Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();
        }
        fragmentTransaction.commit();
        return true;
    }
}
