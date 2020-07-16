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

public class Order extends Fragment implements RecyclerViewClickInterface{

    private List<HashMap<String,Object>> orderItems;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

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

        recyclerView = view.findViewById(R.id.order_view);
        orderAdapter = new OrderAdapter(orderItems, this);
        recyclerView.setAdapter(orderAdapter);

        if(orderItems.size() < 1) {
            completeOrder.setText("Select items from a restaurant first!");
            completeOrder.setClickable(false);
        } else {

            completeOrder.setClickable(true);
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onLongItemClick(int position) {
        orderItems.remove(position);
    }
}