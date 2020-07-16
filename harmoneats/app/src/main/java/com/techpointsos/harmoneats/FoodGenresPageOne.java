package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodGenresPageOne extends AppCompatActivity {

    private Button chineseButton, mexicanButton, bbqButton, meditButton, nextButton, mainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodgenres_page_1);

        chineseButton = (Button) findViewById(R.id.chineseButton);
        mexicanButton = (Button) findViewById(R.id.mexicanButton);
        bbqButton = (Button) findViewById(R.id.bbqButton);
        meditButton = (Button) findViewById(R.id.meditButton);
        nextButton = (Button) findViewById(R.id.nextButton);
        mainButton = (Button) findViewById(R.id.mainFromGenreButton);

        setGenreListenerHelper(chineseButton, "Chinese");
        setGenreListenerHelper(mexicanButton, "Mexican");
        setGenreListenerHelper(bbqButton, "Mexican");
        setGenreListenerHelper(meditButton, "Mediterranean");

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPageIntent = new Intent(getApplicationContext(), FoodGenresPageTwo.class);
                startActivity(nextPageIntent);
            }
        });

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnToMainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(returnToMainIntent);
            }
        });
    }

    public void setGenreListenerHelper(Button button, final String genreName){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent genreSelectIntent = new Intent(getApplicationContext(), SelectedGenrePage.class);
                genreSelectIntent.putExtra("title", genreName);
                genreSelectIntent.putExtra("page return", 1);
                startActivity(genreSelectIntent);
            }
        });
    }

}
