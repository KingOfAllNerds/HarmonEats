package com.techpointsos.harmoneats;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
    }

    public HashMap<String, Object> makeEntry(String name, String description, Icon icon) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("description",description);
        map.put("icon",icon);
        return map;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
