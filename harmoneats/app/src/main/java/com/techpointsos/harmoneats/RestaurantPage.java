package com.techpointsos.harmoneats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RestaurantPage extends Fragment implements RecyclerViewClickInterface{

    private List<HashMap<String,String>> menu;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;

    private String restaurantName;
    private String restaurantDescription;

    public RestaurantPage(String restaurantName, String restaurantDescription) {
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
    }

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

        TextView restaurantName = view.findViewById(R.id.itemName);
        TextView restaurantDescription = view.findViewById(R.id.restaurantDescription);

        restaurantName.setText(this.restaurantName);
        restaurantDescription.setText(this.restaurantDescription);

        menu = new ArrayList<>();

        //TODO: Dynamically take menu and add it to each page on a restaurant basis
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));
        menu.add(makeEntry("Shrimp", "It swims", 3.24));
        menu.add(makeEntry("Burger", "Bun and meat", 6.99));


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
        String itemName = menu.get(position).get("item");
        String itemDescription = menu.get(position).get("description");
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(menu.get(position).get("price")));
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new AddToOrder(itemName,itemDescription,price)).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onLongItemClick(int position) {

    }
}

