package com.techpointsos.harmoneats;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class
Search extends Fragment implements RecyclerViewClickInterface {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<HashMap<String,Object>> restaurants;
    private List<HashMap<String,Object>> filterList;
    private FirebaseFirestore fstore = FirebaseFirestore.getInstance();
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
        fstore.collection("restaurants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Toast.makeText(getActivity(), makeEntry(document.getId(),document.get("description").toString(), null).toString(), Toast.LENGTH_SHORT).show();
                        restaurants.add(makeEntry(document.getId(),document.get("description").toString(), null));
                    }
                    searchAdapter.notifyDataSetChanged();
                }
                else{
                    Log.d("", "Error getting documents: ", task.getException());
                    Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        filterList = restaurants;

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
        filterList = new ArrayList<>();

        if (search == null || search.length() == 0) {
            filterList.addAll(restaurants);
        } else {
            for (HashMap<String,Object> restaurant: restaurants) {

                String name = restaurant.get("name").toString().toLowerCase().trim();
                String description = restaurant.get("description").toString().toLowerCase().trim();
                String searchQuery = search.toLowerCase().trim();

                if(name.contains(searchQuery) || description.contains(searchQuery)) {
                    filterList.add(restaurant);
                }
            }
        }
        searchAdapter.filterList(filterList);
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
        String restaurantName = filterList.get(position).get("name").toString();
        String restaurantDescription = filterList.get(position).get("description").toString();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new RestaurantPage(restaurantName,restaurantDescription)).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onLongItemClick(int position) {

    }
}