package com.example.fragmen_mp2;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView BottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNav = findViewById(R.id.bottom_nav);

        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, homeFragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        BottomNav.setOnItemSelectedListener(item -> {
            int i = item.getItemId();
            Fragment fragment = null;
            switch (i) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;

                case R.id.favorite:
                    fragment = new FavoriteFragment();
                    break;
                case R.id.profile:
                    fragment = new ProfileFragment();
                    break;
            }
            if (fragment != null) {
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

            } else {
                Log.e(TAG, "Error in creating fragment");
            }
            return true;
        });
    }
}