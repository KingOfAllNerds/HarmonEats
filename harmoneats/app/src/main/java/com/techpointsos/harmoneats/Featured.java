package com.techpointsos.harmoneats;

import android.graphics.drawable.Icon;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Featured extends Fragment implements RecyclerViewClickInterface {

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

        featuredList.add(makeEntry("Cafe Patachou", "A student union for adults since 1989!", null));
        featuredList.add(makeEntry("Txuleta Basque Cider House", "A steak-and-cider house that sits on top of their flagship Brugge Brasserie.", null));
        featuredList.add(makeEntry("Beholder", "From the owner of Milktooth, a restaurant that continually refreshers there menu which guarantees a new experience every time.", null));
        featuredList.add(makeEntry("The Inferno Room", "A fantasia of classic Tiki cuisine - sliders to crab rangoon - with amazing classic cocktails.", null));
        featuredList.add(makeEntry("Ukiyo", "Featuring one-of-a-kind 'designer rolls', Neal Brown constantly wows with his fresh menu and fresher fish.", null));
        featuredList.add(makeEntry("Crispy Bird", "From the owners of Cafe Patachou, this fried chicken palace also serves up sides that will make you forget about the colonel.", null));
        featuredList.add(makeEntry("RIZE", "Located in the beautiful Iron Works complex, this restaurant specializes in their delicious brunches.", null));
        featuredList.add(makeEntry("Provision", "Lauded for their drink menu, their cocktail menu and fresh entrees is a truly special corner of culture.", null));
        featuredList.add(makeEntry("Bub's Burgers and Ice Cream", "A cheerful atmosphere and full, reasonably priced menu.  Don't miss out on the elk burger!", null));

        return;
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
        String restaurantName = featuredList.get(position).get("name").toString();
        String restaurantDescription = featuredList.get(position).get("description").toString();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new RestaurantPage(restaurantName,restaurantDescription)).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onLongItemClick(int position) {

    }
}