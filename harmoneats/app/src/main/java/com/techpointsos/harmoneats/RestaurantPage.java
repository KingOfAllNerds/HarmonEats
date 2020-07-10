package com.techpointsos.harmoneats;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantPage extends AppCompatActivity implements RecyclerViewClickInterface {

    List<HashMap<String,String>> menu;
    RecyclerView recyclerView;
    MenuAdapter menuAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent = getIntent();

        TextView restaurantName = findViewById(R.id.restaurantName);
        TextView restaurantDescription = findViewById(R.id.restaurantDescription);

        restaurantName.setText(intent.getStringExtra("name"));
        restaurantDescription.setText(intent.getStringExtra("description"));

        menu = new ArrayList<>();

        //TODO: Dynamically take menu and add it to each page on a restaurant basis
        menu.add(makeEntry("Shrimp","It swims"));
        menu.add(makeEntry("Shrimp","It swims"));
        menu.add(makeEntry("Shrimp","It swims"));
        menu.add(makeEntry("Shrimp","It swims"));
        menu.add(makeEntry("Shrimp","It swims"));
        menu.add(makeEntry("Shrimp","It swims"));
        menu.add(makeEntry("Shrimp","It swims"));

        recyclerView = findViewById(R.id.menuView);
        menuAdapter = new MenuAdapter(menu,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(menuAdapter);
    }

    private HashMap<String, String> makeEntry(String item, String description) {
        HashMap<String, String> map = new HashMap<>();
        map.put("item",item);
        map.put("description",description);
        return map;
    }
    @Override
    public void onItemClick(int position) {
        //TODO: Send to add order page
    }

    @Override
    public void onLongItemClick(int position) {

    }
}
