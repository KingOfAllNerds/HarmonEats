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

        setGenreListenerHelper(italianButton);
        setGenreListenerHelper(indianButton);
        setGenreListenerHelper(seafoodButton);
        setGenreListenerHelper(barAndPubButton);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPageIntent = new Intent(getApplicationContext(), FoodGenresPageOne.class);
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
                genreSelectIntent.putExtra("page to return to", 2);
                startActivity(genreSelectIntent);
            }
        });
    }
}
