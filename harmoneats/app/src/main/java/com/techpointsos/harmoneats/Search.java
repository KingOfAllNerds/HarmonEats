package com.techpointsos.harmoneats;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Search extends Fragment implements RecyclerViewClickInterface {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<HashMap<String,Object>> restaurants;

    public Search() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurants = new ArrayList<HashMap<String,Object>>();

        restaurants.add(makeEntry("Harry & Izzy's", "Upscale steakhouse with a notable wine selection & a contemporary spin on chops, seafood & pizza.", null));
        restaurants.add(makeEntry("Livery", "Stylish bi-level spot with an all-season rooftop patio for Latin classics & lots of tequila.", null));
        restaurants.add(makeEntry("Vida", "Modern American plates & drinks in a trendy space boasting a fireplace, bar & herb wall.", null));
        restaurants.add(makeEntry("Tinker Street Restaurant", "New American outpost with a patio serving wines, brews & seasonal fare in a warm, upbeat setting.", null));
        restaurants.add(makeEntry("The Eagle Mass Ave", "Rustic-chic eatery & beer hall dishing up Southern classics in sprawling digs with a patio.", null));
        restaurants.add(makeEntry("OP Italian Indy", "JW Marriott restaurant serving Italian fare in a sleek space with a bar & glass-enclosed wine room.", null));
        restaurants.add(makeEntry("Milktooth", "Hip, modern diner with a patio for inventive breakfast & brunch items, plus espresso & cocktails.", null));
        restaurants.add(makeEntry("Mama Carolla's", "Upscale Italian restaurant in a 1920s Mediterranean-style villa offering a full bar & a garden.", null));
        restaurants.add(makeEntry("Bru Burger Bar", "Gourmet burgers, creative bar snacks & craft beers in a modern yet cozy space with a patio.", null));
        restaurants.add(makeEntry("Cafe Patachou", "A student union for adults since 1989!", null));
        restaurants.add(makeEntry("Txuleta Basque Cider House", "A steak-and-cider house that sits on top of their flagship Brugge Brasserie.", null));
        restaurants.add(makeEntry("Beholder", "From the owner of Milktooth, a restaurant that continually refreshers there menu which guarantees a new experience every time.", null));
        restaurants.add(makeEntry("The Inferno Room", "A fantasia of classic Tiki cuisine - sliders to crab rangoon - with amazing classic cocktails.", null));
        restaurants.add(makeEntry("Ukiyo", "Featuring one-of-a-kind 'designer rolls', Neal Brown constantly wows with his fresh menu and fresher fish.", null));
        restaurants.add(makeEntry("Crispy Bird", "From the owners of Cafe Patachou, this fried chicken palace also serves up sides that will make you forget about the colonel.", null));
        restaurants.add(makeEntry("RIZE", "Located in the beautiful Iron Works complex, this restaurant specializes in their delicious brunches.", null));
        restaurants.add(makeEntry("Provision", "Lauded for their drink menu, their cocktail menu and fresh entrees is a truly special corner of culture.", null));
        restaurants.add(makeEntry("Bub's Burgers and Ice Cream", "A cheerful atmosphere and full, reasonably priced menu.  Don't miss out on the elk burger!", null));

        EditText editText = view.findViewById(R.id.special_requests);
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

        recyclerView = view.findViewById(R.id.searchView);
        searchAdapter = new SearchAdapter(restaurants, this);
        recyclerView.setAdapter(searchAdapter);
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
        String restaurantName = restaurants.get(position).get("name").toString();
        String restaurantDescription = restaurants.get(position).get("description").toString();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new RestaurantPage(restaurantName,restaurantDescription)).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onLongItemClick(int position) {

    }
}