package com.techpointsos.harmoneats;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();

        TextView restaurantName = findViewById(R.id.restaurantName);
        TextView restaurantDescription = findViewById(R.id.restaurantDescription);

        restaurantName.setText(intent.getStringExtra("name"));
        restaurantDescription.setText(intent.getStringExtra("description"));
    }
}
