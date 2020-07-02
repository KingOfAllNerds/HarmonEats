package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FoodGenresPageTwo extends AppCompatActivity {

    private Button italianButton, indianButton, seafoodButton, barAndPubButton, previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_foodgenres_page_2);

        italianButton = (Button) findViewById(R.id.italianButton);
        indianButton = (Button) findViewById(R.id.indianButton);
        seafoodButton = (Button) findViewById(R.id.seafoodButton);
        barAndPubButton = (Button) findViewById(R.id.barAndPubButton);
        previousButton = (Button) findViewById(R.id.previousButton);

        italianButton.setOnClickListener(new GenreActionListener("Italian"));
        indianButton.setOnClickListener(new GenreActionListener("Indian"));
        seafoodButton.setOnClickListener(new GenreActionListener("Seafood"));
        barAndPubButton.setOnClickListener(new GenreActionListener("Bars & Pubs"));

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPageIntent = new Intent(getApplicationContext(), FoodGenresPageOne.class);
                startActivity(nextPageIntent);
            }
        });
    }
}
