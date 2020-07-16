package com.techpointsos.harmoneats;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

public class AddToOrder extends Fragment {

    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;
    private int itemCount;
    private BigDecimal currentPrice;
    private TextView itemCountBox, itemDescriptionBox, itemNameBox;
    OnItemAddedToOrderListener callback;

    public AddToOrder(String itemName, String itemDescription, BigDecimal itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.currentPrice = itemPrice;
        this.itemCount = 1;
    }


    public void setOnItemAddedToOrderListener(OnItemAddedToOrderListener callback) {
        this.callback = callback;
    }

    public interface OnItemAddedToOrderListener {
        public void onItemAdded(BigDecimal currentPrice, int itemCount ,String itemName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_to_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemNameBox = view.findViewById(R.id.item_name);
        itemDescriptionBox = view.findViewById(R.id.item_description);
        itemCountBox = view.findViewById(R.id.item_count);

        itemNameBox.setText(itemName);
        itemDescriptionBox.setText(itemDescription);
        itemCountBox.setText(String.valueOf(itemCount));

        final Button addToOrderButton = view.findViewById(R.id.add_to_order_button);
        addToOrderButton.setText("Add 1 to Order - $"+itemPrice);

        FloatingActionButton addItem = view.findViewById(R.id.add_item);
        FloatingActionButton removeItem = view.findViewById(R.id.remove_item);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPrice = currentPrice.add(itemPrice);
                itemCount++;
                itemCountBox.setText(String.valueOf(itemCount));
                addToOrderButton.setText("Add " + itemCount + " to Order - $" + currentPrice);
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemCount > 1) {
                    currentPrice = currentPrice.subtract(itemPrice);
                    itemCount--;
                    itemCountBox.setText(String.valueOf(itemCount));
                    addToOrderButton.setText("Add " + itemCount + " to Order - $" + currentPrice);
                }
            }
        });

        addToOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemAdded(currentPrice,itemCount,itemName);
            }
        });
    }
}