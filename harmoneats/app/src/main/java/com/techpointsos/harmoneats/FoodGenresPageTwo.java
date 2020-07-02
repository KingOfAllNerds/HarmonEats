package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

        setGenreListenerHelper(italianButton, "Italian");
        setGenreListenerHelper(indianButton, "Indian");
        setGenreListenerHelper(seafoodButton, "Seafood");
        setGenreListenerHelper(barAndPubButton, "Bars and Pubs");

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPageIntent = new Intent(getApplicationContext(), FoodGenresPageOne.class);
                startActivity(nextPageIntent);
            }
        });
    }

    public void setGenreListenerHelper(Button button, final String genreName){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent genreSelectIntent = new Intent(getApplicationContext(), SelectedGenrePage.class);
                genreSelectIntent.putExtra("title", genreName);
                genreSelectIntent.putExtra("page return", 2);
                startActivity(genreSelectIntent);
            }
        });
    }
}
