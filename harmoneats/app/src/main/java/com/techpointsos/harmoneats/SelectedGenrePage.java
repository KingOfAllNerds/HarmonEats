package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedGenrePage extends AppCompatActivity {

    private Class returnPage;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_genre_page);

        returnButton = (Button) findViewById(R.id.returnButton);

        if(getIntent().hasExtra("title")){
            TextView genreTitle = (TextView) findViewById(R.id.genreTitleTextView);
            String genreText = getIntent().getExtras().getString("title");
            genreTitle.setText(genreText + " Food Page");
        }

        if(getIntent().hasExtra("page return")){
            int pageNumber = getIntent().getExtras().getInt("page return");
            if(pageNumber == 1){
                returnPage = FoodGenresPageOne.class;
            }
            if(pageNumber == 2){
                returnPage = FoodGenresPageTwo.class;
            }
        }

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent(getApplicationContext(), returnPage);
                startActivity(returnIntent);
            }
        });
    }
}
