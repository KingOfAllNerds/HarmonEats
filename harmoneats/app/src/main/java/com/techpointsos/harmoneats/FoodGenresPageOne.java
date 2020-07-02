package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodGenresPageOne extends AppCompatActivity {

    private Button chineseButton, mexicanButton, bbqButton, meditButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodgenres_page_1);

        chineseButton = (Button) findViewById(R.id.chineseButton);
        mexicanButton = (Button) findViewById(R.id.mexicanButton);
        bbqButton = (Button) findViewById(R.id.bbqButton);
        meditButton = (Button) findViewById(R.id.meditButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        setGenreListenerHelper(chineseButton);
        setGenreListenerHelper(mexicanButton);
        setGenreListenerHelper(bbqButton);
        setGenreListenerHelper(meditButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPageIntent = new Intent(getApplicationContext(), FoodGenresPageTwo.class);
                startActivity(nextPageIntent);
            }
        });
    }

    public void setGenreListenerHelper(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent genreSelectIntent = new Intent(getApplicationContext(), SelectedGenrePage.class);
                genreSelectIntent.putExtra("genre was selected", "This is genre page");
                genreSelectIntent.putExtra("page to return to", 1);
                startActivity(genreSelectIntent);
            }
        });
    }

}
