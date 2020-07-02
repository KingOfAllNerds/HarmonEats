package com.techpointsos.harmoneats;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FoodGenresPageOne extends AppCompatActivity {

    private Button chineseButton, mexicanButton, bbqButton, meditButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodgenres_page_one);

        chineseButton = findViewById(R.id.chineseButton);
        mexicanButton = findViewById(R.id.mexicanButton);
        bbqButton = findViewById(R.id.bbqButton);
        meditButton = findViewById(R.id.meditButton);
        nextButton = findViewById(R.id.nextButton);
    }

}
