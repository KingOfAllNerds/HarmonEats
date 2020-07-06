package com.techpointsos.harmoneats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton, featuredButton, searchButton, recommendedButton, orderButton, accountButton, aboutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutButton = (Button) findViewById(R.id.logoutButton);
        featuredButton = (Button) findViewById(R.id.featuredButton);
        searchButton = (Button) findViewById(R.id.searchButton);
        recommendedButton = (Button) findViewById(R.id.recommendedButton);
        orderButton = (Button) findViewById(R.id.orderButton);
        accountButton = (Button) findViewById(R.id.accountButton);
        aboutButton = (Button) findViewById(R.id.accountButton);
        aboutButton = (Button) findViewById(R.id.accountButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

        featuredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent genreIntent = new Intent(getApplicationContext(), FoodGenresPageOne.class);
                startActivity(genreIntent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

        recommendedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}