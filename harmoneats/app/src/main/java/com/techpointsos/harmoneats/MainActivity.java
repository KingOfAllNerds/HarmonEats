package com.techpointsos.harmoneats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigationBar);
        bottomNavigationView.setSelectedItemId(R.id.fragment_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

//        logoutButton = (Button) findViewById(R.id.logoutButton);
//
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                logout(view);
//            }
//        });

    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    Search searchFragment = new Search();
    Order orderFragment = new Order();
    Recommended recommendedFragment = new Recommended();
    Account accountFragment = new Account();
    Featured featuredFragment = new Featured();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.fragment_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, searchFragment).commit();
                return true;
            case R.id.fragment_recommended:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recommendedFragment).commit();
                return true;
            case R.id.fragment_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, accountFragment).commit();
                return true;
            case R.id.fragment_featured:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, featuredFragment).commit();
                return true;
            case R.id.fragment_order:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, orderFragment).commit();
                return true;
        }
        return false;
    }
}