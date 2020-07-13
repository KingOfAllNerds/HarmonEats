package com.techpointsos.harmoneats;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RestaurantPage extends Fragment implements RecyclerViewClickInterface{

    List<HashMap<String,String>> menu;
    RecyclerView recyclerView;
    MenuAdapter menuAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_restaurant_page, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.menuView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView restaurantName = view.findViewById(R.id.restaurantName);
        TextView restaurantDescription = view.findViewById(R.id.restaurantDescription);

        menu = new ArrayList<>();

        //TODO: Dynamically take menu and add it to each page on a restaurant basis
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));


        recyclerView = view.findViewById(R.id.menuView);
        menuAdapter = new MenuAdapter(menu, this);

        recyclerView.setAdapter(menuAdapter);
    }

    private HashMap<String, String> makeEntry(String item, String description, Double price) {
        HashMap<String, String> map = new HashMap<>();
        map.put("item",item);
        map.put("description",description);
        map.put("price", price.toString());
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

