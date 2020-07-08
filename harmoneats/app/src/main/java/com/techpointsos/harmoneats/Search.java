package com.techpointsos.harmoneats;

import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search extends AppCompatActivity {

    RecyclerView searchView;
    SearchAdapter searchAdapter;
    List<HashMap<String,Object>> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        restaurants = new ArrayList<HashMap<String,Object>>();
        searchView = findViewById(R.id.searchView);
        searchAdapter = new SearchAdapter(restaurants);

        searchView.setLayoutManager(new LinearLayoutManager(this));

        searchView.setAdapter(searchAdapter);

        restaurants.add(makeEntry("Lavergne's", "Tavern", null));
        restaurants.add(makeEntry("Chili's", "Why bro?", null));
        restaurants.add(makeEntry("Raising Canes", "Hell yeah", null));
        
    }

    public HashMap<String, Object> makeEntry(String name, String description, Icon icon) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("description",description);
        map.put("icon",icon);
        return map;
    }
}
