package com.techpointsos.harmoneats;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order extends Fragment {

    private List<HashMap<String,Object>> orderItems;

    public Order(List<HashMap<String,Object>> orderItems) {
        this.orderItems = orderItems;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.order_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button completeOrder = view.findViewById(R.id.complete_order);
        if(orderItems.size() < 1) {
            completeOrder.setText("Check out - $0.00");
        } else {
            completeOrder.setText("YAY");
        }
    }
}