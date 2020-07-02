package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectedGenrePage extends AppCompatActivity {

    private Class returnPage;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_genre_page);

        returnButton = (Button) findViewById(R.id.returnButton);

        if(getIntent().hasExtra("genre was selected")){
            TextView pageNumberDeclaration = (TextView) findViewById(R.id.selectGenreTextView);
            String declarationText = getIntent().getExtras().getString("genre was selected");
            pageNumberDeclaration.setText(declarationText);
        }

        if(getIntent().hasExtra("page to return to")){
            int pageNumber = getIntent().getExtras().getInt("page to return to");
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
