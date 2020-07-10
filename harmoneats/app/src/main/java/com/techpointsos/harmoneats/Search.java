package com.techpointsos.harmoneats;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search extends AppCompatActivity implements RecyclerViewClickInterface{

    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    List<HashMap<String,Object>> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        restaurants = new ArrayList<HashMap<String,Object>>();
        recyclerView = findViewById(R.id.searchView);
        searchAdapter = new SearchAdapter(restaurants,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(searchAdapter);

        restaurants.add(makeEntry("Harry & Izzy's", "Upscale steakhouse with a notable wine selection & a contemporary spin on chops, seafood & pizza.", null));
        restaurants.add(makeEntry("Livery", "Stylish bi-level spot with an all-season rooftop patio for Latin classics & lots of tequila.", null));
        restaurants.add(makeEntry("Vida", "Modern American plates & drinks in a trendy space boasting a fireplace, bar & herb wall.", null));
        restaurants.add(makeEntry("Tinker Street Restaurant", "New American outpost with a patio serving wines, brews & seasonal fare in a warm, upbeat setting.", null));
        restaurants.add(makeEntry("The Eagle Mass Ave", "Rustic-chic eatery & beer hall dishing up Southern classics in sprawling digs with a patio.", null));
        restaurants.add(makeEntry("OP Italian Indy", "JW Marriott restaurant serving Italian fare in a sleek space with a bar & glass-enclosed wine room.", null));
        restaurants.add(makeEntry("Milktooth", "Hip, modern diner with a patio for inventive breakfast & brunch items, plus espresso & cocktails.", null));
        restaurants.add(makeEntry("Mama Carolla's", "Upscale Italian restaurant in a 1920s Mediterranean-style villa offering a full bar & a garden.\n", null));
        restaurants.add(makeEntry("Bru Burger Bar", "Gourmet burgers, creative bar snacks & craft beers in a modern yet cozy space with a patio.", null));

        EditText editText = findViewById(R.id.searchBar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String search) {
        List<HashMap<String,Object>> filteredList = new ArrayList<>();

        if (search == null || search.length() == 0) {
            filteredList.addAll(restaurants);
        } else {
            for (HashMap<String,Object> restaurant: restaurants) {

                String name = restaurant.get("name").toString().toLowerCase().trim();
                String description = restaurant.get("description").toString().toLowerCase().trim();
                String searchQuery = search.toLowerCase().trim();

                if(name.contains(searchQuery) || description.contains(searchQuery)) {
                    filteredList.add(restaurant);
                }
            }
        }
        searchAdapter.filterList(filteredList);
    }

    private HashMap<String, Object> makeEntry(String name, String description, Icon icon) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("description",description);
        map.put("icon",icon);
        return map;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(),RestaurantPage.class);
        intent.putExtra("name",(String) restaurants.get(position).get("name"));
        intent.putExtra("description",(String) restaurants.get(position).get("description"));
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}
