package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FoodGenresPageOne extends AppCompatActivity {

    private Button chineseButton, mexicanButton, bbqButton, meditButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodgenres_page_1);

        chineseButton = findViewById(R.id.chineseButton);
        mexicanButton = findViewById(R.id.mexicanButton);
        bbqButton = findViewById(R.id.bbqButton);
        meditButton = findViewById(R.id.meditButton);
        nextButton = findViewById(R.id.nextButton);

        chineseButton.setOnClickListener(new GenreActionListener("Chinese"));
        mexicanButton.setOnClickListener(new GenreActionListener("Mexican"));
        bbqButton.setOnClickListener(new GenreActionListener("BBQ"));
        meditButton.setOnClickListener(new GenreActionListener("Mediterranean"));

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPageIntent = new Intent(getApplicationContext(), FoodGenresPageTwo.class);
                startActivity(nextPageIntent);
            }
        });
    }

}
