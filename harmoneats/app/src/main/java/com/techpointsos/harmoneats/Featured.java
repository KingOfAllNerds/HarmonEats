package com.techpointsos.harmoneats;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Featured extends Fragment implements RecyclerViewClickInterface {

    private RecyclerView featuredRecylerView;
    private List<HashMap<String, Object>> featuredList;
    private SearchAdapter searchAdapter;
    private FirebaseFirestore fstore = FirebaseFirestore.getInstance();

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

        featuredList = new ArrayList<HashMap<String, Object>>();
        //Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
        fstore.collection("restaurants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Toast.makeText(getActivity(), makeEntry(document.getId(),document.get("description").toString(), null).toString(), Toast.LENGTH_SHORT).show();
                        featuredList.add(makeEntry(document.getId(),document.get("description").toString(), null));
                    }
                    searchAdapter.notifyDataSetChanged();
                }
                else{
                    Log.d("", "Error getting documents: ", task.getException());
                    Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        searchAdapter = new SearchAdapter(featuredList, this);
        featuredRecylerView.setAdapter(searchAdapter);

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