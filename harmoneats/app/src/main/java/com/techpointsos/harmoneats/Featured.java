package com.techpointsos.harmoneats;

import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Featured extends Fragment implements RecyclerViewClickInterface {

    private TextView featured_requests;
    private RecyclerView featuredRecylerView;
    private List<HashMap<String, Object>> featuredList;
    private SearchAdapter searchAdapter;

    public Featured() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_featured, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.featuredRecylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        featuredRecylerView = (RecyclerView) view.findViewById(R.id.featuredRecylerView);

        continueOnViewCreated();    //Don't want to clutter one method with hardcoding.

        searchAdapter = new SearchAdapter(featuredList, this);
        featuredRecylerView.setAdapter(searchAdapter);

    }

    public void continueOnViewCreated(){
        featuredList = new ArrayList<HashMap<String, Object>>();

        featuredList.add(makeEntry("Harry & Izzy's", "Upscale steakhouse with a notable wine selection & a contemporary spin on chops, seafood & pizza.", null));
        featuredList.add(makeEntry("Livery", "Stylish bi-level spot with an all-season rooftop patio for Latin classics & lots of tequila.", null));
        featuredList.add(makeEntry("Vida", "Modern American plates & drinks in a trendy space boasting a fireplace, bar & herb wall.", null));
        featuredList.add(makeEntry("Tinker Street Restaurant", "New American outpost with a patio serving wines, brews & seasonal fare in a warm, upbeat setting.", null));
        featuredList.add(makeEntry("The Eagle Mass Ave", "Rustic-chic eatery & beer hall dishing up Southern classics in sprawling digs with a patio.", null));
        featuredList.add(makeEntry("OP Italian Indy", "JW Marriott restaurant serving Italian fare in a sleek space with a bar & glass-enclosed wine room.", null));
        featuredList.add(makeEntry("Milktooth", "Hip, modern diner with a patio for inventive breakfast & brunch items, plus espresso & cocktails.", null));
        featuredList.add(makeEntry("Mama Carolla's", "Upscale Italian restaurant in a 1920s Mediterranean-style villa offering a full bar & a garden.", null));
        featuredList.add(makeEntry("Bru Burger Bar", "Gourmet burgers, creative bar snacks & craft beers in a modern yet cozy space with a patio.", null));

        return;
    }

    private HashMap<String, Object> makeEntry(String name, String description, Icon icon) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("description",description);
        map.put("icon",icon);
        return map;
    }

    private void filter(String search) {
        List<HashMap<String,Object>> filteredList = new ArrayList<>();

        if (search == null || search.length() == 0) {
            filteredList.addAll(featuredList);
        } else {
            for (HashMap<String,Object> restaurant: featuredList) {

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

    @Override
    public void onItemClick(int position) {
        String restaurantName = featuredList.get(position).get("name").toString();
        String restaurantDescription = featuredList.get(position).get("description").toString();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new RestaurantPage(restaurantName,restaurantDescription));
        fragmentTransaction.commit();
    }

    @Override
    public void onLongItemClick(int position) {

    }
}